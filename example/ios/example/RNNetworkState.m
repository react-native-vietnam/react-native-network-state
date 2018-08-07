//
//  RNNetworkState.m
//
//  Created by Anh Tuan Nguyen on 8/7/18.
//  Copyright © 2018 ReactNativeVietnam. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RNNetworkState.h"
#import <AFnetworking/AFNetworkReachabilityManager.h>

@implementation RNNetworkState {
}

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"networkChanged"];
}

- (void)startObserving {
}

- (void)stopObserving {
}

-(BOOL) isInternetReachable
{
  return [AFNetworkReachabilityManager sharedManager].reachable;
}
RCT_EXPORT_MODULE()

@end
