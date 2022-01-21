import Foundation
import Capacitor
import Lottie

@objc(CapacitorLottieSplashScreenPlugin)
public class CapacitorLottieSplashScreenPlugin: CAPPlugin {
    private let implementation = CapacitorLottieSplashScreen()
    private var animationView: AnimationView?
    
    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
    
    override public func load() {
        loadLottie()
    }

    func loadLottie() {
        if let view =  self.bridge?.viewController?.view,
            let path = self.getConfigValue("LottieAnimationLocation") as? String,
            let filename = path.components(separatedBy: ".").first {
            print("CapacitorLottieSplashScreenPlugin =====>>>  found")
            animationView = .init(name: filename)
            animationView!.frame = UIScreen.main.bounds
            animationView!.contentMode = .scaleAspectFit
            animationView!.loopMode = .playOnce
            animationView!.animationSpeed = 1
            view.addSubview(animationView!)
            animationView!.play { completed in
                if completed {
                    self.animationView?.removeFromSuperview()
                }
            }
        } else {
            print("CapacitorLottieSplashScreenPlugin =====>>> NOT found")
        }
    }
}
