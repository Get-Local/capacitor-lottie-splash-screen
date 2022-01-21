import { WebPlugin } from '@capacitor/core';

import type { CapacitorLottieSplashScreenPlugin } from './definitions';

export class CapacitorLottieSplashScreenWeb
  extends WebPlugin
  implements CapacitorLottieSplashScreenPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
