//
//  RNNetworkState.m
//
//  Created by Anh Tuan Nguyen on 8/7/18.
//  Copyright © 2018 ReactNativeVietnam. All rights reserved.
//

#import "RNNetworkState.h"
#import <AFnetworking/AFNetworkReachabilityManager.h>

@implementation RNNetworkState {
  BOOL hasListeners;
}

RCT_EXPORT_MODULE()
@synthesize bridge = _bridge;

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

- (void)startObserving {
  hasListeners = YES;
  
}

- (void)stopObserving {
  hasListeners = NO;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"networkChanged"];
}

- (instancetype) init {
  self = [super init];
  if (self) {
    [[AFNetworkReachabilityManager sharedManager] startMonitoring];
  }
  return self;
}

//[[AFNetworkReachabilityManager sharedManager] setReachabilityStatusChangeBlock:^(AFNetworkReachabilityStatus status) {
//  NSLog(@"Reachability changed: %@", AFStringFromNetworkReachabilityStatus(status));
//
//  NSDictionary *data = @{@"isConnected": @YES, @"isFast": @NO, @"type": @[AFStringFromNetworkReachabilityStatus(status)] };
//  //      switch (status) {
//  //        case AFNetworkReachabilityStatusReachableViaWWAN: {
//  //          [data setValue:@YES forKey:@"isConnected"];
//  //          [data setValue:@YES forKey:@"isFast"];
//  //          break;
//  //        }
//  //        case AFNetworkReachabilityStatusReachableViaWiFi: {
//  //          // -- Reachable -- //
//  //          NSLog(@"Reachable");
//  //          [data setValue:@YES forKey:@"isConnected"];
//  //          [data setValue:@YES forKey:@"isFast"];
//  //          break;
//  //        }
//  //        case AFNetworkReachabilityStatusNotReachable: {
//  //          [data setValue:@NO forKey:@"isConnected"];
//  //          [data setValue:@NO forKey:@"isFast"];
//  //          break;
//  //        }
//  //        default: {
//  //          // -- Not reachable -- //
//  //          NSLog(@"Not Reachable");
//  //          [data setValue:@NO forKey:@"isConnected"];
//  //          [data setValue:@NO forKey:@"isFast"];
//  //          break;
//  //        }
//  //      }
//
//  if(hasListeners) {
//    NSLog(@"AAAAAAAAAA");
//    [self sendEventWithName:@"networkChanged" body:data];
//  } else {
//    NSLog(@"CCCCCCCCCC");
//  }
//}];

@end
