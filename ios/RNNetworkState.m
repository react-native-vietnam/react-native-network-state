
#import "RNNetworkState.h"

@implementation RNNetworkState {
    BOOL hasListeners;
}

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
    if([timer isValid]) {
        [timer invalidate];
    }
    timer = nil;
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
    if([self bridge] != nil && hasListeners) {
        [self sendEventWithName:@"pingPong" body:data];
    }
}

- (NSArray<NSString *> *)supportedEvents {
    return @[@"pingPong"];
}

- (void)startObserving {
    hasListeners = YES;
}

- (void)stopObserving {
    hasListeners = NO;
    [self stopPing];
}
@end