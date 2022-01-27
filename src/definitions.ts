import { PluginListenerHandle } from "@capacitor/core";

export interface CapacitorLottieSplashScreenPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  appLoaded(): Promise<{}>;
  isAnimating(): Promise<{ isAnimating: boolean }>;
  addListener(eventName: 'onAnimationEnd', listenerFunc: () => void): Promise<PluginListenerHandle> & PluginListenerHandle;
}
