
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

RCT_EXPORT_METHOD(startPing:(nonnull NSNumber *) interval) {
    [NSTimer scheduledTimerWithTimeInterval: [interval doubleValue] target: self selector:@selector(ping:) userInfo:nil repeats:YES];
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
    [self sendEventWithName:@"pingPong" body:isAvailable ? @YES : @NO];
}

- (NSArray<NSString *> *)supportedEvents {
    return @[@"pingPong"];
}

@end