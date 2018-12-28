//
//  CDVViewMap.h
//
//  Created by chenlounai on 2018/11/28.
//
#import <Cordova/CDVPlugin.h>



//iOS插件是以继承至CDVPlugin类Objective-C的子类来实现的，通过JavaScript的exec函数来将service/parameter映射到Objective-C类
@interface CDVnotchScreen : CDVPlugin
- (void)isNotchScreen:(CDVInvokedUrlCommand*)command;
- (void)getNotchParams:(CDVInvokedUrlCommand*)command;

@end
