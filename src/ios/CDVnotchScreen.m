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
         CGFloat a = [[UIApplication sharedApplication ] delegate].window.safeAreaInsets.bottom;
         if(a>0){
            CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK  messageAsString:@"true"] ;
            [self.commandDelegate sendPluginResult:result callbackId: command.callbackId];
            
        }
    }
    CDVPluginResult* result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK  messageAsString:@"false"] ;
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];

 
}
 

// 获取刘海屏的参数
- (void)getNotchParams:(CDVInvokedUrlCommand*)command
{ 

     
} 

@end
