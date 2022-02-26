import Foundation
import Lottie
import UIKit

@objc public enum AnimationEventListener: Int {
    case onAnimationEnd
}

extension AnimationEventListener {
    var listenerEvent: String {
        switch self {
        case .onAnimationEnd:
            return "onAnimationEnd"
        }
    }
}

@objc public class CapacitorLottieSplashScreen: NSObject {
    
    
    private var animationView: AnimationView?
    private var isAppLoaded = false
    private var isAnimationEnded = !CapacitorLottieSplashScreenPlugin.isEnabledStatic
    
    public typealias AnimationEventListenerCallback = (AnimationEventListener) -> Void

    @objc public var onAnimationEvent: AnimationEventListenerCallback?
    
    public func isAnimating () -> Bool {
        return !isAnimationEnded
    }
    
    func onAppLoaded() {
        isAppLoaded = true
        if(isAnimationEnded) {
            hideSplashScreen()
        }
    }
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
    public func loadLottie(view: UIView?, path: String?) {
        if let view = view,
           let path = path,
           let filename = path.components(separatedBy: ".").first {
            animationView = .init(name: filename)
            animationView!.frame = UIScreen.main.bounds
            animationView!.contentMode = .scaleAspectFill
            animationView!.loopMode = .playOnce
            animationView!.animationSpeed = 1
            view.addSubview(animationView!)
            animationView!.play { completed in
                if completed {
                    self.isAnimationEnded = true
                    if(self.isAppLoaded){
                        self.hideSplashScreen()
                    }
                    self.onAnimationEvent?(.onAnimationEnd)
                }
            }
        }
    }
    
    func hideSplashScreen() {
        self.animationView?.removeFromSuperview()
    }
}
