# @get-local/capacitor-lottie-splash-screen

Lottie splash screen plugin for capacitor

## Install

```bash
npm install @get-local/capacitor-lottie-splash-screen
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`appLoaded()`](#apploaded)
* [`isAnimating()`](#isanimating)
* [`addListener('onAnimationEnd', ...)`](#addlisteneronanimationend)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### appLoaded()

```typescript
appLoaded() => Promise<{}>
```

**Returns:** <code>Promise&lt;{}&gt;</code>

--------------------


### isAnimating()

```typescript
isAnimating() => Promise<{ isAnimating: boolean; }>
```

**Returns:** <code>Promise&lt;{ isAnimating: boolean; }&gt;</code>

--------------------


### addListener('onAnimationEnd', ...)

```typescript
addListener(eventName: 'onAnimationEnd', listenerFunc: () => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                          |
| ------------------ | ----------------------------- |
| **`eventName`**    | <code>'onAnimationEnd'</code> |
| **`listenerFunc`** | <code>() =&gt; void</code>    |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |

</docgen-api>
