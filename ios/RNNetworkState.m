//
//  RCTNetworkStatus.m
//
//  Created by Anh Tuan Nguyen on 8/8/18.
//  Copyright © 2018 ReactNativeVietnam. All rights reserved.
//

#import "RNNetworkState.h"
#import "Reachability.h"

@implementation RNNetworkState {
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
- (void) openSetting:(NSURL *) url {
  [[UIApplication sharedApplication] openURL:url options:@{} completionHandler:nil];
}
RCT_EXPORT_MODULE();
RCT_EXPORT_METHOD(openGeneral) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=General"]];
}
RCT_EXPORT_METHOD(openPrivacy) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=Privacy"]];
}
RCT_EXPORT_METHOD(openWifi) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=WIFI"]];
}
RCT_EXPORT_METHOD(openAirplaneMode) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=AIRPLANE_MODE"]];
}
RCT_EXPORT_METHOD(openNote) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=NOTES"]];
}
RCT_EXPORT_METHOD(openWireless) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=MOBILE_DATA_SETTINGS_ID"]];
}
RCT_EXPORT_METHOD(openiCloud) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=CASTLE"]];
}
RCT_EXPORT_METHOD(openNotifications) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=NOTIFICATIONS_ID"]];
}
RCT_EXPORT_METHOD(openInputMethod) {
  [self openSetting:[NSURL URLWithString:@"App-prefs:root=General&path=Keyboard/KEYBOARDS"]];
}

@end
