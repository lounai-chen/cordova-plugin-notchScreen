//
//  CDVMaps.m
//
//  Created by chenlounai on 2018/11/28.
//

#import "CDVnotchScreen.h"
@implementation CDVnotchScreen

//  判断是否有刘海屏
- (void)isNotchScreen:(CDVInvokedUrlCommand*)command
{
     if (@available(iOS 11.0, *)) {
        if (!UIEdgeInsetsEqualToEdgeInsets(self.view.safeAreaInsets, UIEdgeInsetsZero)) {
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK  messageAsString:@"true"] ;
            [self.commandDelegate sendPluginResult:result callbackId:initCallback];
            return YES;
        }
    }
    CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK  messageAsString:@"false"] ;
    [self.commandDelegate sendPluginResult:result callbackId:initCallback];
    return NO;
 
}
 

// 获取刘海屏的参数
- (void)getNotchParams:(CDVInvokedUrlCommand*)command
{ 

     
} 

@end
