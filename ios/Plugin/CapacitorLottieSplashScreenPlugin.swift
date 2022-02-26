import Foundation
import Capacitor

@objc(CapacitorLottieSplashScreenPlugin)
public class CapacitorLottieSplashScreenPlugin: CAPPlugin {
    public static var isEnabledStatic = true
    private let implementation = CapacitorLottieSplashScreen()
    
    
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
    
    override public func load() {
        if(CapacitorLottieSplashScreenPlugin.isEnabledStatic){
            let isEnabled = getConfigValue("Enabled") as? Bool ?? true
            if(isEnabled) {
                implementation.loadLottie(view: self.bridge?.viewController?.view, path: self.getConfigValue("LottieAnimationLocation") as? String)
            }
            implementation.onAnimationEvent = onAnimationEvent
        }
    }
    
    public func onAnimationEvent(event: AnimationEventListener) {
        print("onAnimationEvent",event.listenerEvent)
        self.bridge?.triggerWindowJSEvent(eventName: event.listenerEvent)
        self.notifyListeners(event.listenerEvent, data: nil)
    }
    
    @objc func appLoaded(_ call: CAPPluginCall) {
        implementation.onAppLoaded()
        call.resolve()
    }
    
    @objc func isAnimating(_ call: CAPPluginCall) {
        call.resolve([
            "isAnimating": implementation.isAnimating()
        ])
    }
    
}
