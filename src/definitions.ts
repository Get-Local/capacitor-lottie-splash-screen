export interface CapacitorLottieSplashScreenPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
