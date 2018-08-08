//
//  RCTNetworkStatus.m
//
//  Created by Anh Tuan Nguyen on 8/8/18.
//  Copyright © 2018 ReactNativeVietnam. All rights reserved.
//

#import "RNNetworkStatus.h"
#import "Reachability.h"

@implementation RNNetworkStatus {
  Reachability* reach;
  BOOL hasListener;
  NetworkStatus prevStatus;
  NSTimer *timer;
}
- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}
+ (BOOL)requiresMainQueueSetup {
  return YES;
}
- (void)startObserving {
  hasListener = @YES;
  timer = [NSTimer scheduledTimerWithTimeInterval: 1 target: self selector:@selector(monitorNetwork:) userInfo:nil repeats:YES];
}
- (void)stopObserving {
  hasListener = @NO;
  if([timer isValid]) {
    [timer invalidate];
  }
  timer = nil;
}
- (NSArray<NSString *> *)supportedEvents {
  return @[@"networkChanged"];
}
- (instancetype)init
{
  self = [super init];
  if (self) {
    reach = [Reachability reachabilityWithHostname:@"www.google.com"];
    prevStatus = [reach currentReachabilityStatus];
  }
  return self;
}

- (void) monitorNetwork:(NSTimer *) monitorNetwork {
  NetworkStatus status = [reach currentReachabilityStatus];
  if(prevStatus == status) {
    return;
  }
  prevStatus = status;
  BOOL isConnected = (status == ReachableViaWWAN || status == ReachableViaWiFi);
  NSString *type = [reach currentReachabilityString];
  NSDictionary *data = @{@"isConnected": isConnected ? @YES : @NO,
                         @"type": type,
                         @"isFast": isConnected ? @YES : @NO
                        };
  if([self bridge] == nil || !hasListener) {
    return;
  }
  [self sendEventWithName:@"networkChanged" body:data];
}
RCT_EXPORT_MODULE();
@end
