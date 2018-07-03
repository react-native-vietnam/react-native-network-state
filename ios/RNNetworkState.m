
#import "RNNetworkState.h"

@implementation RNNetworkState

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
    return YES;
}

RCT_EXPORT_MODULE()

NSTimer *timer = nil;

RCT_EXPORT_METHOD(startPing:(nonnull NSNumber *) interval) {
    timer = [NSTimer scheduledTimerWithTimeInterval: [interval doubleValue] target: self selector:@selector(ping:) userInfo:nil repeats:YES];
}

RCT_EXPORT_METHOD(stopPing) {
    if(timer != nil && [timer isValid]) {
        [timer invalidate];
        timer = nil;
    }
}

- (void) ping:(NSTimer *) timer {
    bool success = false;
    const char *host_name = [@"google.com"
                             cStringUsingEncoding:NSASCIIStringEncoding];
    
    SCNetworkReachabilityRef reachability = SCNetworkReachabilityCreateWithName(NULL,
                                                                                host_name);
    SCNetworkReachabilityFlags flags;
    success = SCNetworkReachabilityGetFlags(reachability, &flags);
    CFRelease(reachability);
    
    bool isAvailable = success && (flags & kSCNetworkFlagsReachable) &&
    !(flags & kSCNetworkFlagsConnectionRequired);

    NSDictionary *data = @{@"connected": isAvailable ? @YES : @NO};
    dispatch_async(dispatch_get_main_queue(), ^{
        if([self bridge] != nil) {
            [self sendEventWithName:@"pingPong" body:data];
        }
    });
}

- (NSArray<NSString *> *)supportedEvents {
    return @[@"pingPong"];
}

@end