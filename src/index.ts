import { registerPlugin } from '@capacitor/core';

import type { CapacitorLottieSplashScreenPlugin } from './definitions';

const CapacitorLottieSplashScreen = registerPlugin<CapacitorLottieSplashScreenPlugin>(
  'CapacitorLottieSplashScreen',
  {
    web: () =>
      import('./web').then(m => new m.CapacitorLottieSplashScreenWeb()),
  },
);

export * from './definitions';
export { CapacitorLottieSplashScreen };
