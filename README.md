# Affise Attribution Android Library

[Change Log](CHANGELOG.md)

| Artifact                         | Version                                                                                                                                                                         |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `com.affise:attribution`         | [![attribution](https://img.shields.io/maven-central/v/com.affise/attribution?label=latest)](https://mvnrepository.com/artifact/com.affise/attribution)                         |
| `com.affise:module-advertising`  | [![module-advertising](https://img.shields.io/maven-central/v/com.affise/module-advertising?label=latest)](https://mvnrepository.com/artifact/com.affise/module-advertising)    |
| `com.affise:module-androidid`    | [![module-androidid](https://img.shields.io/maven-central/v/com.affise/module-androidid?label=latest)](https://mvnrepository.com/artifact/com.affise/module-androidid)          |
| `com.affise:module-appsflyer`    | [![module-appsflyer](https://img.shields.io/maven-central/v/com.affise/module-appsflyer?label=latest)](https://mvnrepository.com/artifact/com.affise/module-appsflyer)          |
| `com.affise:module-link`         | [![module-link](https://img.shields.io/maven-central/v/com.affise/module-link?label=latest)](https://mvnrepository.com/artifact/com.affise/module-link)                         |
| `com.affise:module-network`      | [![module-network](https://img.shields.io/maven-central/v/com.affise/module-network?label=latest)](https://mvnrepository.com/artifact/com.affise/module-network)                |
| `com.affise:module-phone`        | [![module-phone](https://img.shields.io/maven-central/v/com.affise/module-phone?label=latest)](https://mvnrepository.com/artifact/com.affise/module-phone)                      |
| `com.affise:module-status`       | [![module-status](https://img.shields.io/maven-central/v/com.affise/module-status?label=latest)](https://mvnrepository.com/artifact/com.affise/module-status)                   |
| `com.affise:module-subscription` | [![module-subscription](https://img.shields.io/maven-central/v/com.affise/module-subscription?label=latest)](https://mvnrepository.com/artifact/com.affise/module-subscription) |
| `com.affise:module-rustore`      | [![module-rustore](https://img.shields.io/maven-central/v/com.affise/module-rustore?label=latest)](https://mvnrepository.com/artifact/com.affise/module-rustore)                |
| `com.affise:module-huawei`       | [![module-huawei](https://img.shields.io/maven-central/v/com.affise/module-huawei?label=latest)](https://mvnrepository.com/artifact/com.affise/module-huawei)                   |
| `com.affise:module-meta`         | [![module-meta](https://img.shields.io/maven-central/v/com.affise/module-meta?label=latest)](https://mvnrepository.com/artifact/com.affise/module-meta)                         |
| `com.affise:module-tiktok`       | [![module-tiktok](https://img.shields.io/maven-central/v/com.affise/module-tiktok?label=latest)](https://mvnrepository.com/artifact/com.affise/module-tiktok)                   |

- [Affise Attribution Android Library](#affise-attribution-android-library)
- [Description](#description)
  - [Quick start](#quick-start)
    - [SDK compatibility](#sdk-compatibility)
  - [Integration](#integration)
    - [Integrate as dependency](#integrate-as-dependency)
    - [Integrate as file dependency](#integrate-as-file-dependency)
    - [Initialize](#initialize)
      - [Before application is published](#before-application-is-published)
      - [Domain](#domain)
    - [Modules](#modules)
      - [Module Advertising](#module-advertising)
      - [Module AppsFlyer](#module-appsflyer)
      - [Module Huawei](#module-huawei)
      - [Module Link](#module-link)
      - [Module Meta](#module-meta)
      - [Module Status](#module-status)
      - [Module Subscription](#module-subscription)
        - [AffiseProductType](#affiseproducttype)
      - [Module TikTok](#module-tiktok)
    - [Requirements](#requirements)
- [Features](#features)
  - [ProviderType identifiers collection](#providertype-identifiers-collection)
    - [Attribution](#attribution)
    - [Advertising](#advertising)
    - [AndroidId](#androidid)
    - [Huawei](#huawei)
    - [Meta](#meta)
    - [Network](#network)
    - [Phone](#phone)
  - [Event send control](#event-send-control)
  - [Events tracking](#events-tracking)
  - [Custom events tracking](#custom-events-tracking)
  - [Predefined event parameters](#predefined-event-parameters)
    - [PredefinedString](#predefinedstring)
    - [PredefinedLong](#predefinedlong)
    - [PredefinedFloat](#predefinedfloat)
    - [PredefinedObject](#predefinedobject)
    - [PredefinedListObject](#predefinedlistobject)
    - [PredefinedListString](#predefinedliststring)
  - [Events buffering](#events-buffering)
  - [Advertising Identifier (google) tracking](#advertising-identifier-google-tracking)
  - [Open Advertising Identifier (huawei) tracking](#open-advertising-identifier-huawei-tracking)
  - [Install referrer tracking](#install-referrer-tracking)
  - [Push token tracking](#push-token-tracking)
  - [Reinstall Uninstall tracking](#reinstall-uninstall-tracking)
  - [APK preinstall tracking](#apk-preinstall-tracking)
  - [Links](#links)
    - [Deeplinks](#deeplinks)
    - [AppLinks](#applinks)
    - [Get deferred deeplink](#get-deferred-deeplink)
    - [Get deferred deeplink value](#get-deferred-deeplink-value)
  - [Offline mode](#offline-mode)
  - [Disable tracking](#disable-tracking)
  - [Disable background tracking](#disable-background-tracking)
  - [GDPR right to be forgotten](#gdpr-right-to-be-forgotten)
  - [Get referrer](#get-referrer)
  - [Get referrer parameter](#get-referrer-parameter)
  - [Referrer keys](#referrer-keys)
  - [Get module state](#get-module-state)
  - [Get random user Id](#get-random-user-id)
  - [Get random device Id](#get-random-device-id)
  - [Get providers](#get-providers)
  - [Is first run](#is-first-run)
  - [WebView tracking](#webview-tracking)
    - [Initialize WebView](#initialize-webview)
    - [Events tracking JS](#events-tracking-js)
    - [Predefined event parameters JS](#predefined-event-parameters-js)
    - [Custom events JS](#custom-events-js)
  - [Custom](#custom)
    - [ConversionId](#conversionid)
- [SDK to SDK integrations](#sdk-to-sdk-integrations)
  - [AdMob](#admob)
  - [AppLovin MAX](#applovin-max)
  - [Helium by Chartboost](#helium-by-chartboost)
  - [ironSource](#ironsource)
  - [Admost](#admost)
- [Debug](#debug)
  - [Validate credentials](#validate-credentials)
  - [Version](#version)
    
# Description

Affise SDK is a software you can use to collect app usage statistics, device identifiers, deeplink usage, track install
referrer.

## Quick start

### SDK compatibility

- `API` `24+`

## Integration

### Integrate as dependency

For kotlin build script build.gradle.kts use:

```kotlin
val affise_version = "1.6.62"

dependencies {
  // Add Affise library 
  implementation("com.affise:attribution:$affise_version")
  // Add Affise modules 
  implementation("com.affise:module-advertising:$affise_version")
  implementation("com.affise:module-androidid:$affise_version")
  implementation("com.affise:module-link:$affise_version")
  implementation("com.affise:module-network:$affise_version")
  implementation("com.affise:module-phone:$affise_version")
  implementation("com.affise:module-status:$affise_version")
  implementation("com.affise:module-subscription:$affise_version")
  // implementation("com.affise:module-appsflyer:$affise_version")
  // implementation("com.affise:module-rustore:$affise_version")
  // implementation("com.affise:module-huawei:$affise_version")
//   implementation("com.affise:module-meta:$affise_version")
//   implementation("com.affise:module-tiktok:$affise_version")
  // Add install referrer
  implementation("com.android.installreferrer:installreferrer:2.2")
}
```

For groovy build script build.gradle use:

```groovy
final affise_version = '1.6.62'

dependencies {
    // Add Affise library 
    implementation "com.affise:attribution:$affise_version"
    // Add Affise modules 
    implementation "com.affise:module-advertising:$affise_version"
    implementation "com.affise:module-androidid:$affise_version"
    implementation "com.affise:module-link:$affise_version"
    implementation "com.affise:module-network:$affise_version"
    implementation "com.affise:module-phone:$affise_version"
    implementation "com.affise:module-status:$affise_version"
    implementation "com.affise:module-subscription:$affise_version"
    // implementation "com.affise:module-appsflyer:$affise_version"
    // implementation "com.affise:module-rustore:$affise_version"
    // implementation "com.affise:module-huawei:$affise_version"
    // implementation "com.affise:module-meta:$affise_version"
    // implementation "com.affise:module-tiktok:$affise_version"
    // Add install referrer
    implementation 'com.android.installreferrer:installreferrer:2.2'
}
```

### Integrate as file dependency

Download latest Affise SDK (`attribution-1.6.62.aar`)
from [releases page](https://github.com/affise/affise-mmp-sdk-android/releases) and place this binary to gradle application
module lib directory `app/libs/attribution-1.6.62.aar`

Add library as gradle file dependency to application module build script
Add install referrer library

For kotlin build script build.gradle.kts use:

```kotlin
val affise_version = "1.6.62"

dependencies {
    // ...
    // Add Affise library 
    implementation(files("libs/attribution-1.6.62.aar"))
    // Add Affise modules 
    implementation(files("libs/module-advertising-$affise_version.aar"))
    implementation(files("libs/module-androidid-$affise_version.aar"))
    implementation(files("libs/module-link-$affise_version.aar"))
    implementation(files("libs/module-network-$affise_version.aar"))
    implementation(files("libs/module-phone-$affise_version.aar"))
    implementation(files("libs/module-status-$affise_version.aar"))
    implementation(files("libs/module-subscription-$affise_version.aar"))
    // implementation(files("libs/module-appsflyer-$affise_version.aar"))
    // implementation(files("libs/module-rustore-$affise_version.aar"))
    // implementation(files("libs/module-huawei-$affise_version.aar"))
    // implementation(files("libs/module-meta-$affise_version.aar"))
    // implementation(files("libs/module-tiktok-$affise_version.aar"))
    // Add install referrer
    implementation("com.android.installreferrer:installreferrer:2.2")
}
```

For groovy build script build.gradle use:

```groovy
final affise_version = '1.6.62'

dependencies {
  // ...  
  // Add Affise library 
  implementation files("libs/attribution-${affise_version}.aar")
  // Add Affise modules 
  implementation files("libs/module-advertising-${affise_version}.aar")
  implementation files("libs/module-androidid-${affise_version}.aar")
  implementation files("libs/module-link-${affise_version}.aar")
  implementation files("libs/module-network-${affise_version}.aar")
  implementation files("libs/module-phone-${affise_version}.aar")
  implementation files("libs/module-status-${affise_version}.aar")
  implementation files("libs/module-subscription-${affise_version}.aar")
  // implementation files("libs/module-appsflyer-${affise_version}.aar")
  // implementation files("libs/module-rustore-${affise_version}.aar")
  // implementation files("libs/module-huawei-${affise_version}.aar")
  // implementation files("libs/module-meta-${affise_version}.aar")
  // implementation files("libs/module-tiktok-${affise_version}.aar")
  // Add install referrer
  implementation 'com.android.installreferrer:installreferrer:2.2'
}
```

### Initialize

After library is added as dependency sync project with gradle files and initialize.

> Demo app [App.kt](app/src/main/java/com/affise/app/App.kt)

For kotlin use:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        Affise
            .settings(
                affiseAppId = "Your appId", //Change to your app id
                secretKey = "Your SDK secretKey", //Change to your SDK secretKey
            )
            .start(this) // Start Affise SDK
    }
}
```

For java use:

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
      
        Affise
            .settings(
                "Your appId", //Change to your app id
                "Your SDK secretKey" //Change to your SDK secretKey
            )
            .start(this); // Start Affise SDK
    }
}
```

Check if library is initialized

```kotlin
Affise
    .settings(
        affiseAppId = "Your appId",
        secretKey = "Your SDK secretKey",
    )
    .setOnInitSuccess {
        // Called then library is initialized
    }
    .start(this)
```

#### Before application is published

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> Please make sure your credentials are valid
>
> Visit section [validation credentials](#validate-credentials)
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

#### Domain

Set SDK server domain:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        Affise
            .settings(
                affiseAppId = "Your appId",
                secretKey = "Your SDK secretKey",
            )
            .setDomain("https://YoureCustomDomain/") // Set custom domain
            .start(this) // Start Affise SDK
    }
}
```

### Modules

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
> 
> How to install modules read in [Integration section](#integration)
> 
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

| Module         | Version                                                                                                                                                                         | Start  |
|:---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------:|
| `Advertising`  | [![module-advertising](https://img.shields.io/maven-central/v/com.affise/module-advertising?label=latest)](https://mvnrepository.com/artifact/com.affise/module-advertising)    | `Auto` |
| `AndroidId`    | [![module-androidid](https://img.shields.io/maven-central/v/com.affise/module-androidid?label=latest)](https://mvnrepository.com/artifact/com.affise/module-androidid)          | `Auto` |
| `AppsFlyer`    | [![module-appsflyer ](https://img.shields.io/maven-central/v/com.affise/module-appsflyer?label=latest)](https://mvnrepository.com/artifact/com.affise/module-appsflyer)         | `Auto` |
| `Link`         | [![module-link](https://img.shields.io/maven-central/v/com.affise/module-link?label=latest)](https://mvnrepository.com/artifact/com.affise/module-link)                         | `Auto` |
| `Network`      | [![module-network](https://img.shields.io/maven-central/v/com.affise/module-network?label=latest)](https://mvnrepository.com/artifact/com.affise/module-network)                | `Auto` |
| `Phone`        | [![module-phone](https://img.shields.io/maven-central/v/com.affise/module-phone?label=latest)](https://mvnrepository.com/artifact/com.affise/module-phone)                      | `Auto` |
| `Status`       | [![module-status](https://img.shields.io/maven-central/v/com.affise/module-status?label=latest)](https://mvnrepository.com/artifact/com.affise/module-status)                   | `Auto` |
| `Subscription` | [![module-subscription](https://img.shields.io/maven-central/v/com.affise/module-subscription?label=latest)](https://mvnrepository.com/artifact/com.affise/module-subscription) | `Auto` |
| `RuStore`      | [![module-rustore](https://img.shields.io/maven-central/v/com.affise/module-rustore?label=latest)](https://mvnrepository.com/artifact/com.affise/module-rustore)                | `Auto` |
| `Huawei`       | [![module-huawei](https://img.shields.io/maven-central/v/com.affise/module-huawei?label=latest)](https://mvnrepository.com/artifact/com.affise/module-huawei)                   | `Auto` |
| `Meta`         | [![module-meta](https://img.shields.io/maven-central/v/com.affise/module-meta?label=latest)](https://mvnrepository.com/artifact/com.affise/module-meta)                         | `Auto` |
| `TikTok`       | [![module-tiktok](https://img.shields.io/maven-central/v/com.affise/module-tiktok?label=latest)](https://mvnrepository.com/artifact/com.affise/module-tiktok)                   | `Auto` |

If module start type is `Manual`, then call:

```kotlin
Affise.Module.moduleStart(AffiseModules.Advertising)
```

Get list of installed modules:

```kotlin
Affise.Module.getModulesInstalled()
```

#### Module Advertising

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> For module `Advertising` to send GAID (Google Advertising ID)
> 
> Uses `com.google.android.gms:play-services-ads-identifier`
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

```kotlin
val gaid = Affise.getProviders()[ProviderType.GAID_ADID] as? String
```

#### Module AppsFlyer

Send AppsFlyer event data to Affise 

```kotlin
//AppsFlyer event data
val eventValues: Map<String, Any> = mapOf(
    AFInAppEventParameterName.PRICE to 1234.56,
    AFInAppEventParameterName.CONTENT_ID to "1234567",
)
// Send AppsFlyer event
AppsFlyerLib.getInstance().logEvent(
    applicationContext,
    AFInAppEventType.ADD_TO_WISH_LIST, eventValues
)
// Send AppsFlyer data to Affise
Affise.Module.AppsFlyer.logEvent(AFInAppEventType.ADD_TO_WISH_LIST, eventValues)
```

Is Module present:

```kotlin
Affise.Module.AppsFlyer.hasModule()
```

#### Module Huawei

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> Use [Huawei Module](#modules) to get OAID (Open Advertising Identifier)
>
> Uses `com.huawei.hms:ads-identifier`
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

```kotlin
val oaid = Affise.getProviders()[ProviderType.OAID] as? String
```

#### Module Link

Return last url in chan of redirection

🟥Support MAX 10 redirections🟥

```kotlin
Affise.Module.Link.resolve("SITE_WITH_REDIRECTION") { redirectUrl ->
    // handle redirect url
}
```

For java use:

```java
Affise.Module.Link.resolve("SITE_WITH_REDIRECTION", redirectUrl -> {
    // handle redirect url  
});
```

Is Module present:

```kotlin
Affise.Module.Link.hasModule()
```

#### Module Meta

- [Meta Install Referrer Docs](https://developers.facebook.com/docs/app-ads/meta-install-referrer)
- [Google's Install Referral Docs](https://developer.android.com/google/play/installreferrer)

1. Add `queries` to your `AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <queries>
        <package android:name="com.facebook.katana" />
        <package android:name="com.instagram.android" />
        <package android:name="com.facebook.lite" />
    </queries>

    <application>
      ...
    </application>
</manifest>
```

2. Add your `Facebook App Id` as config value `AffiseConfig.FbAppId` in `Affise.settings`

```kotlin
Affise
    .settings(
        affiseAppId = "Your appId",
        secretKey = "Your SDK secretKey",
    )
    .setConfigValue(AffiseConfig.FbAppId, "Your Facebook App Id")
    .start(this)
```

#### Module Status

```kotlin
Affise.Module.getStatus(AffiseModules.Status) { response ->
  // handle response
}
```

#### Module Subscription

Get products by ids:

```kotlin
val ids = ["exampple.product.id_1", "exampple.product.id_2"]

Affise.Module.Subscription.fetchProducts(ids) { result ->
    when (result) {
        is AffiseResult.Success -> {
          val products: List<AffiseProduct> = result.value.products
          val invalidIds: List<String> = result.value.invalidIds
        }
        is AffiseResult.Error -> {
          val error: String = result.error.message
        }
    }
}
```

Purchase product:

```kotlin
// Specify AffiseProductType for correct affise event
Affise.Module.Subscription.purchase(activity, product, AffiseProductType.CONSUMABLE) { result ->
    when (result) {
        is AffiseResult.Success -> {
          val purchasedInfo: AffisePurchasedInfo  = result.value
        }
        is AffiseResult.Error -> {
          val error: String = result.error.message
        }
    }
}
```

Is Module present:

```kotlin
Affise.Module.Subscription.hasModule()
```

##### AffiseProductType

- `CONSUMABLE`
- `NON_CONSUMABLE`
- `RENEWABLE_SUBSCRIPTION`
- `NON_RENEWABLE_SUBSCRIPTION`


#### Module TikTok

Required `com.github.tiktok:tiktok-business-android-sdk:1.5.0`

Send TikTok event data to Affise [TikTok Docs](https://business-api.tiktok.com/gateway/docs/index?identify_key=c0138ffadd90a955c1f0670a56fe348d1d40680b3c89461e09f78ed26785164b&language=ENGLISH&doc_id=1739585434183746#item-link-3.2.2%20Content%20parameters%20for%20standard%20events)

```kotlin
//TikTok event data
val addToCartEvent = TTAddToCartEvent.newBuilder("TikTok event id")
    .setDescription("product1")//Description of the item or page.
    .setCurrency(TTContentsEventConstants.Currency.JPY)//The ISO 4217 currency code.
    .setValue(123.4)//Value of the order or items sold.
    .setContents(
        TTContentParams.newBuilder()//Relevant products in an event with product information.
            .setContentId("item1")//Unique ID of the product or content.
            .setContentCategory("game")//Category of the page or product.
            .setBrand("tt")//Brand name of the product item.
            .setPrice(123.4f)//The price of the item.
            .setQuantity(1)//The number of items.
            .setContentName("item1 test")
            .build()
    )//Name of the page or product.
    .setContentType("IAP")//The type of content in the event.
    .build()

TikTokBusinessSdk.trackTTEvent(addToCartEvent)

// Send TikTok data to Affise
Affise.Module.TikTok.sendEvent(addToCartEvent)
```

Is Module present:

```kotlin
Affise.Module.TikTok.hasModule()
```

### Requirements

For a minimal working functionality your app needs to declare internet permission:

```xml

<manifest>
  <!-- ... -->
  <uses-permission android:name="android.permission.INTERNET"/>
</manifest>
```

# Features

## ProviderType identifiers collection

To match users with events and data library is sending, these `ProviderType` identifiers are collected:

### Attribution

- `AFFISE_APP_ID`
- `AFFISE_PKG_APP_NAME`
- `AFF_APP_NAME_DASHBOARD`
- `APP_VERSION`
- `APP_VERSION_RAW`
- `STORE`
- `TRACKER_TOKEN`
- `TRACKER_NAME`
- `FIRST_TRACKER_TOKEN`
- `FIRST_TRACKER_NAME`
- `LAST_TRACKER_TOKEN`
- `LAST_TRACKER_NAME`
- `OUTDATED_TRACKER_TOKEN`
- `INSTALLED_TIME`
- `FIRST_OPEN_TIME`
- `INSTALLED_HOUR`
- `FIRST_OPEN_HOUR`
- `INSTALL_FIRST_EVENT`
- `INSTALL_BEGIN_TIME`
- `INSTALL_FINISH_TIME`
- `REFERRER_INSTALL_VERSION`
- `REFERRAL_TIME`
- `REFERRER_CLICK_TIME`
- `REFERRER_CLICK_TIME_SERVER`
- `REFERRER_GOOGLE_PLAY_INSTANT`
- `CREATED_TIME`
- `CREATED_TIME_MILLI`
- `CREATED_TIME_HOUR`
- `UNINSTALL_TIME`
- `REINSTALL_TIME`
- `LAST_SESSION_TIME`
- `CPU_TYPE`
- `HARDWARE_NAME`
- `DEVICE_MANUFACTURER`
- `DEEPLINK_CLICK`
- `DEVICE_ATLAS_ID`
- `AFFISE_DEVICE_ID`
- `AFFISE_ALT_DEVICE_ID`
- `REFTOKEN`
- `REFTOKENS`
- `REFERRER`
- `USER_AGENT`
- `MCCODE`
- `MNCODE`
- `REGION`
- `COUNTRY`
- `LANGUAGE`
- `DEVICE_NAME`
- `DEVICE_TYPE`
- `OS_NAME`
- `PLATFORM`
- `SDK_PLATFORM`
- `API_LEVEL_OS`
- `AFFISE_SDK_VERSION`
- `OS_VERSION`
- `RANDOM_USER_ID`
- `AFFISE_SDK_POS`
- `TIMEZONE_DEV`
- `AFFISE_EVENT_NAME`
- `AFFISE_EVENT_TOKEN`
- `LAST_TIME_SESSION`
- `TIME_SESSION`
- `AFFISE_SESSION_COUNT`
- `LIFETIME_SESSION_COUNT`
- `AFFISE_DEEPLINK`
- `AFFISE_PART_PARAM_NAME`
- `AFFISE_PART_PARAM_NAME_TOKEN`
- `AFFISE_APP_TOKEN`
- `LABEL`
- `AFFISE_SDK_SECRET_ID`
- `UUID`
- `AFFISE_APP_OPENED`
- `PUSHTOKEN`
- `PUSHTOKEN_SERVICE`
- `AFFISE_EVENTS_COUNT`
- `AFFISE_SDK_EVENTS_COUNT`
- `AFFISE_METRICS_EVENTS_COUNT`
- `AFFISE_INTERNAL_EVENTS_COUNT`
- `IS_ROOTED`
- `IS_EMULATOR`

### Advertising

- `GAID_ADID`
- `GAID_ADID_MD5`
- `ADID`
- `ALTSTR_ADID`
- `FIREOS_ADID`
- `COLOROS_ADID`
- `AD_PERSONALIZATION`

### AndroidId

- `ANDROID_ID`
- `ANDROID_ID_MD5`

### Huawei

- `OAID`
- `OAID_MD5`

### Meta

- `META`

### Network

- `MAC_SHA1`
- `MAC_MD5`
- `CONNECTION_TYPE`
- `PROXY_IP_ADDRESS`

### Phone

- `NETWORK_TYPE`
- `ISP`

## Event send control

There are two ways to send events

1. Cache event to later scheduled send in batch

```kotlin
AddToCartEvent()
    .send()
```

2. Send event right now

```kotlin
AddToCartEvent()
    .sendNow({
        // handle event send success
    }) { errorResponse ->
      // handle event send failed
      // 🟥Warning:🟥 event is NOT cached for later send
    }
```

## Events tracking

> Demo app [DefaultEventsFactory.kt](app/src/main/java/com/affise/app/ui/screen/buttons/factories/DefaultEventsFactory.kt)

For example, we want to track what items usually user adds to shopping cart. To send event first create it with
following code

```kotlin
class Presenter {
  fun onUserAddsItemsToCart(userData: String) {
    AddToCartEvent(userData)
        .send() // Send event
  }
}
```

For java use:

```java
class Presenter {
  void onUserAddsItemsToCart(String userData) {
    new AddToCartEvent(userData)
            .send(); // Send event
  }
}
```

With above example you can implement other events:

- `AchieveLevel`
- `AddPaymentInfo`
- `AddToCart`
- `AddToWishlist`
- `AdRevenue`
- `ClickAdv`
- `CompleteRegistration`
- `CompleteStream`
- `CompleteTrial`
- `CompleteTutorial`
- `Contact`
- `ContentItemsView`
- `CustomizeProduct`
- `DeepLinked`
- `Donate`
- `FindLocation`
- `InitiateCheckout`
- `InitiatePurchase`
- `InitiateStream`
- `Invite`
- `LastAttributedTouch`
- `Lead`
- `ListView`
- `Login`
- `OpenedFromPushNotification`
- `Order`
- `OrderItemAdded`
- `OrderItemRemove`
- `OrderCancel`
- `OrderReturnRequest`
- `OrderReturnRequestCancel`
- `Purchase`
- `Rate`
- `ReEngage`
- `Reserve`
- `Sales`
- `Schedule`
- `Search`
- `Share`
- `SpendCredits`
- `StartRegistration`
- `StartTrial`
- `StartTutorial`
- `SubmitApplication`
- `Subscribe`
- `TravelBooking`
- `UnlockAchievement`
- `Unsubscribe`
- `Update`
- `ViewAdv`
- `ViewCart`
- `ViewContent`
- `ViewItem`
- `ViewItems`
- `InitialSubscription`
- `InitialTrial`
- `InitialOffer`
- `ConvertedTrial`
- `ConvertedOffer`
- `TrialInRetry`
- `OfferInRetry`
- `SubscriptionInRetry`
- `RenewedSubscription`
- `FailedSubscriptionFromRetry`
- `FailedOfferFromRetry`
- `FailedTrialFromRetry`
- `FailedSubscription`
- `FailedOfferise`
- `FailedTrial`
- `ReactivatedSubscription`
- `RenewedSubscriptionFromRetry`
- `ConvertedOfferFromRetry`
- `ConvertedTrialFromRetry`
- `Unsubscription`

## Custom events tracking

Use any of custom events if default doesn't fit your scenario:

- `CustomId01`
- `CustomId02`
- `CustomId03`
- `CustomId04`
- `CustomId05`
- `CustomId06`
- `CustomId07`
- `CustomId08`
- `CustomId09`
- `CustomId10`

If above event functionality still limits your usecase, you can use `UserCustomEvent`

```kotlin
UserCustomEvent("MyCustomNameEvent")
  .addPredefinedParameter(PredefinedString.DESCRIPTION, "best before 2029")
  .addPredefinedParameter(PredefinedObject.CONTENT, JSONObject().apply {
    put("collection", "Greatest Hits")
  })
  .send() // Send event
```

## Predefined event parameters

To enrich your event with another dimension, you can use predefined parameters for most common cases.
Add it to any event:

```kotlin
class Presenter {
  fun onUserAddsItemsToCart(userData: String) {
    AddToCartEvent(userData)
        .addPredefinedParameter(PredefinedString.DESCRIPTION, "best before 2029")
        .addPredefinedParameter(PredefinedObject.CONTENT, JSONObject().apply {
          put("collection", "Greatest Hits")
        })
        .addPredefinedParameter(PredefinedListObject.CONTENT_LIST, listOf(
          JSONObject().apply {
            put("content", "songs, videos")
          }
        ))
        .send() // Send event
  }
}
```

For java use:

```java
class Presenter {
  void onUserAddsItemsToCart(String userData) {
    JSONObject json = new JSONObject()
            .put("collection", "Greatest Hits");
    
    JSONObject jsonContent = new JSONObject()
            .put("content", "songs, videos");
    
    List<JSONObject> jsonList = Collections.singletonList(jsonContent);
    
    new AddToCartEvent(userData, System.currentTimeMillis())
            .addPredefinedParameter(PredefinedString.DESCRIPTION, "best before 2029")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedObject.CONTENT, json)
            .addPredefinedParameter(PredefinedListObject.CONTENT_LIST, jsonList)
            .send(); // Send event
  }
}
```

In examples above `PredefinedString.DESCRIPTION` and `PredefinedFloat.PRICE` is used, but many others is available:

| PredefinedParameter                           | Type                   |
|-----------------------------------------------|------------------------|
| [PredefinedString](#predefinedstring)         | String                 |
| [PredefinedLong](#predefinedlong)             | Long                   |
| [PredefinedFloat](#predefinedfloat)           | Float                  |
| [PredefinedObject](#predefinedobject)         | JSONObject             |
| [PredefinedListObject](#predefinedlistobject) | List&lt;JSONObject&gt; |
| [PredefinedListString](#predefinedliststring) | List&lt;String&gt;     |

### PredefinedString

- `ACHIEVEMENT_ID`
- `ADREV_AD_TYPE`
- `BRAND`
- `BRICK`
- `CAMPAIGN_ID`
- `CATALOGUE_ID`
- `CHANNEL_TYPE`
- `CITY`
- `CLASS`
- `CLICK_ID`
- `CONTENT_ID`
- `CONTENT_NAME`
- `CONTENT_TYPE`
- `CONVERSION_ID`
- `COUNTRY`
- `COUPON_CODE`
- `CURRENCY`
- `CUSTOMER_SEGMENT`
- `CUSTOMER_TYPE`
- `CUSTOMER_USER_ID`
- `DEEP_LINK`
- `DESCRIPTION`
- `DESTINATION_A`
- `DESTINATION_B`
- `DESTINATION_LIST`
- `EVENT_NAME`
- `NEW_VERSION`
- `NETWORK`
- `OLD_VERSION`
- `ORDER_ID`
- `PARAM_01`
- `PARAM_02`
- `PARAM_03`
- `PARAM_04`
- `PARAM_05`
- `PARAM_06`
- `PARAM_07`
- `PARAM_08`
- `PARAM_09`
- `PARAM_10`
- `PAYMENT_INFO_AVAILABLE`
- `PID`
- `PLACEMENT`
- `PREFERRED_NEIGHBORHOODS`
- `PRODUCT_ID`
- `PRODUCT_NAME`
- `PURCHASE_CURRENCY`
- `RECEIPT_ID`
- `REGION`
- `REGISTRATION_METHOD`
- `REVIEW_TEXT`
- `SEARCH_STRING`
- `SEGMENT`
- `SOURCE`
- `STATUS`
- `SUBSCRIPTION_ID`
- `SUCCESS`
- `SUGGESTED_DESTINATIONS`
- `SUGGESTED_HOTELS`
- `TUTORIAL_ID`
- `UNIT`
- `UTM_CAMPAIGN`
- `UTM_MEDIUM`
- `UTM_SOURCE`
- `VALIDATED`
- `VERTICAL`
- `VIRTUAL_CURRENCY_NAME`
- `VOUCHER_CODE`

### PredefinedLong

- `AMOUNT`
- `DATE_A`
- `DATE_B`
- `DEPARTING_ARRIVAL_DATE`
- `DEPARTING_DEPARTURE_DATE`
- `HOTEL_SCORE`
- `LEVEL`
- `MAX_RATING_VALUE`
- `NUM_ADULTS`
- `NUM_CHILDREN`
- `NUM_INFANTS`
- `PREFERRED_NUM_STOPS`
- `PREFERRED_STAR_RATINGS`
- `QUANTITY`
- `RATING_VALUE`
- `RETURNING_ARRIVAL_DATE`
- `RETURNING_DEPARTURE_DATE`
- `SCORE`
- `TRAVEL_START`
- `TRAVEL_END`
- `USER_SCORE`
- `EVENT_START`
- `EVENT_END`

### PredefinedFloat

- `PREFERRED_PRICE_RANGE`
- `PRICE`
- `REVENUE`
- `LAT`
- `LONG`

### PredefinedObject

- `CONTENT`

### PredefinedListObject

- `CONTENT_LIST`

### PredefinedListString

- `CONTENT_IDS`

## Events buffering

Affise library will send any pending events with first opportunity,
but if there is no network connection or device is disabled, events are kept locally for 7 days before deletion.

## Advertising Identifier (google) tracking

> **Note**
>
> Requires [Affise Advertising Module](#modules)

Advertising Identifier (google) tracking is supported automatically, no actions needed

## Open Advertising Identifier (huawei) tracking

> **Note**
>
> Requires [Affise Huawei Module](#modules)

Open Advertising Identifier is supported automatically, no actions needed

## Install referrer tracking

Install referrer tracking is supported automatically, no actions needed

## Push token tracking

To let affise track push token you need to receive it from your push service provider, and pass to Affise library.
First add firebase integration to your app completing these steps: [Firebase Docs](https://firebase.google.com/docs/cloud-messaging/android/client)

After you have done with firebase integration, add to your cloud messaging service `onNewToken` method `Affise.addPushToken(token)`

Example [PushService.kt](app/src/main/java/com/affise/app/PushService.kt):

```kotlin
class FirebaseCloudMessagingService : FirebaseMessagingService() {
  override fun onNewToken(token: String) {
    // New token generated
    Affise.addPushToken(token)
  }
}
```

## Reinstall Uninstall tracking

Affise automatically track reinstall events by using silent-push technology, 
to make this feature work, pass push token when it is recreated by user and on you application starts up

See example [Push token tracking](#push-token-tracking)

## APK preinstall tracking

SDK is also supports scenario when APK is installed not from one of application markets, such as google play, huawei appgallery or amazon appstore
To use this feature, create file with name `partner_key` in your app assets directory, and write unique identifier inside, this key will be passed to our backend so you can track events by partner later in your Affise console.

## Links

### Deeplinks

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> Deeplinks support only **CUSTOM** scheme **NOT** `http` or `https`
>
> For `http` or `https` read how to setup [AppLinks](#applinks)
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

To integrate deeplink support you need:

- Add intent filter to one of your activities
- Add **custom** scheme (**NOT** `http` or `https`) and host to filter

Example: `YOUR_SCHEME://YOUR_DOMAIN`

Example: `myapp://mydomain.com`

```xml
<intent-filter android:autoVerify="true">
  <action android:name="android.intent.action.VIEW" />
  <category android:name="android.intent.category.DEFAULT" />
  <category android:name="android.intent.category.BROWSABLE" />
  <data android:scheme="YOUR_SCHEME" />
  <data android:host="YOUR_DOMAIN" />
</intent-filter>
```

- Register deeplink callback right after `Affise.settings(..).start(..)`

for kotlin:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.registerDeeplinkCallback { value ->
    // full uri "scheme://host/path?parameters"   
    val deeplink = value.deeplink
    
    // separated for convenience 
    val scheme = value.scheme
    val host = value.host
    val path = value.path
    val queryParametersMap = value.parameters
    
    if(queryParametersMap["<your_uri_key>"].contains("<your_uri_key_value>")) {
        // handle value
    }
}
```

for java:

```java
Affise.registerDeeplinkCallback(value -> {
    // full uri "scheme://host/path?parameters"   
    String deeplink = value.getDeeplink();

    // separated for convenience 
    String scheme = value.getScheme();
    String host = value.getHost();
    String path = value.getPath();
    Map<String, List<String>> queryParametersMap = value.getParameters();
    
    if (queryParametersMap.get("your_uri_key").contains("your_uri_key_value")) {
        // handle value
    }
});
```

Test DeepLink via terminal command:

```terminal
adb shell am start -a android.intent.action.VIEW -d "YOUR_SCHEME://YOUR_DOMAIN/somepath?param=1\&list=some\&list=other\&list="
```

### AppLinks

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> You must own website domain.
>
> And has ability to add file `https://yoursite/.well-known/assetlinks.json`
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

To integrate applink support you need:

- Add intent filter to one of your activities
- Add `https` or `http` scheme and host to filter

Example: `https://YOUR_DOMAIN`

Example: `https://mydomain.com`

```xml
<intent-filter android:autoVerify="true">
  <action android:name="android.intent.action.VIEW" />
  <category android:name="android.intent.category.DEFAULT" />
  <category android:name="android.intent.category.BROWSABLE" />
  <data android:scheme="https" />
  <data android:host="YOUR_DOMAIN" />
</intent-filter>
```

- Associate your app with your website. [Read Google instructions](https://developer.android.com/studio/write/app-link-indexing#associatesite) <details>
  <summary>How To Associate your app with your website</summary>
  
  ---

  After setting up URL support for your app, the App Links Assistant generates a Digital Assets Links file you can use to [associate your website with your app](https://developer.android.com/training/app-links/verify-android-applinks#web-assoc).

  As an alternative to using the Digital Asset Links file, you can [associate your site and app in Search Console](https://support.google.com/webmasters/answer/6212023).

  If you're using [Play App Signing](https://support.google.com/googleplay/android-developer/answer/9842756) for your app, then the certificate fingerprint produced by the App Links Assistant usually doesn't match the one on users' devices. In this case, you can find the correct Digital Asset Links JSON snippet for your app in your [Play Console](https://play.google.com/console/) developer account under **Release** > **Setup** > **App signing**.

  To associate your app and your website using the App Links Assistant, click **Open Digital Asset Links File Generator** from the App Links Assistant and follow these steps:

  ![app-links-assistant-dal-file-generator_2x](https://developer.android.com/static/studio/images/write/app-links-assistant-dal-file-generator_2x.png)
  **Figure 2**. Enter details about your site and app to generate a Digital Asset Links file.

  1. Enter your **Site domain** and your [**Application ID**](https://developer.android.com/studio/build/configure-app-module#set-application-id).
  
  2. To include support in your Digital Asset Links file for [One Tap sign-in](https://developers.google.com/identity/one-tap/android/overview), select **Support sharing credentials between the app and the website** and enter your site's sign-in URL.This adds the following string to your Digital Asset Links file declaring that your app and website share sign-in credentials: `delegate_permission/common.get_login_creds`.

  3. Specify the [signing config](https://developer.android.com/studio/publish/app-signing#sign-auto) or select a [keystore file](https://developer.android.com/studio/publish/app-signing#certificates-keystores).

  Make sure you select the right release config or keystore file for the release build or the debug config or keystore file for the debug build of your app. If you want to set up your production build, use the release config. If you want to test your build, use the debug config.

  4. Click **Generate Digital Asset Links file**.
  5. Once Android Studio generates the file, click **Save file** to download it.
  6. Upload the `assetlinks.json` file to your site, with read access for everyone, at `https://yoursite/.well-known/assetlinks.json`.

  > **Important**
  >
  > The system verifies the Digital Asset Links file via the encrypted HTTPS protocol. Make sure that the **assetlinks.json** file is accessible over an HTTPS connection, regardless of whether your app's intent filter includes **https**.

  7. Click **Link and Verify** to confirm that you've uploaded the correct Digital Asset Links file to the correct location.

  Learn more about associating your website with your app through the Digital Asset Links file in Declare website associations.
  
  ---

</details>

- Register deeplink callback right after `Affise.settings(..).start(..)`

for kotlin:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.registerDeeplinkCallback { value ->
    // full uri "scheme://host/path?parameters"   
    val deeplink = value.deeplink
  
    // separated for convenience 
    val scheme = value.scheme
    val host = value.host
    val path = value.path
    val queryParametersMap = value.parameters
  
    if(queryParametersMap["<your_uri_key>"].contains("<your_uri_key_value>")) {
        // handle value
    }
}
```

for java:

```java
Affise.registerDeeplinkCallback(value -> {
    // full uri "scheme://host/path?parameters"   
    String deeplink = value.getDeeplink();
    
    // separated for convenience 
    String scheme = value.getScheme();
    String host = value.getHost();
    String path = value.getPath();
    Map<String, List<String>> queryParametersMap = value.getParameters();
      
    if (queryParametersMap.get("your_uri_key").contains("your_uri_key_value")) {
        // handle value
    }
});
```

Test AppLinks via terminal command:

```terminal
adb shell am start -a android.intent.action.VIEW -d "https://YOUR_DOMAIN/somepath?param=1\&list=some\&list=other\&list="
```


### Get deferred deeplink

> **Note**
>
> Requires [Affise Status Module](#modules)

Use the next public method of SDK

```kotlin
Affise.getDeferredDeeplink { deferredDeeplink ->
  // handle deferred deeplink
}
```

### Get deferred deeplink value

> **Note**
>
> Requires [Affise Status Module](#modules)

Use the next public method of SDK to get referrer parameter by

```kotlin
Affise.getDeferredDeeplinkValue(ReferrerKey.CLICK_ID) { deferredDeeplinkValue ->
  // handle deferred deeplink value
}
```

## Offline mode

In some scenarios you would want to limit Affise network usage, to pause that activity call anywhere in your application following code after Affise init:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.setOfflineModeEnabled(true) // to enable offline mode
Affise.setOfflineModeEnabled(false) // to disable offline mode
```

While offline mode is enabled, your metrics and other events are kept locally, and will be delivered once offline mode is disabled.
Offline mode is persistent as Application lifecycle, and will be disabled with process termination automatically.
To check current offline mode status call:

```kotlin
Affise.isOfflineModeEnabled() // returns true or false describing current tracking state
```

## Disable tracking

To disable any tracking activity, storing events and gathering device identifiers and metrics call anywhere in your application following code after Affise init:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.setTrackingEnabled(true) // to enable tracking
Affise.setTrackingEnabled(false) // to disable tracking
```

By default tracking is enabled.

While tracking mode is disabled, metrics and other identifiers is not generated locally.
Keep in mind that this flag is persistent until app reinstall, and don't forget to reactivate tracking when needed.
To check current status of tracking call:

```kotlin
Affise.isTrackingEnabled() // returns true or false describing current tracking state
```

## Disable background tracking

To disable any background tracking activity, storing events and gathering device identifiers and metrics call anywhere in your application following code after Affise init:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.setBackgroundTrackingEnabled(true) // to enable background tracking
Affise.setBackgroundTrackingEnabled(false) // to disable background tracking
```

By default background tracking is enabled.

While background tracking mode is disabled, metrics and other identifiers is not generated locally.
Background tracking mode is persistent as Application lifecycle, and will be re-enabled with process termination automatically.
To check current status of background tracking call:

```kotlin
Affise.isBackgroundTrackingEnabled() // returns true or false describing current background tracking state
```

## GDPR right to be forgotten

Under the EU's General Data Protection Regulation (GDPR): An individual has the right to have their personal data erased.
To provide this functionality to user, as the app developer, you can call

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.forget() // to forget users data
```

After processing such request our backend servers will delete all users data.
To prevent library from generating new events, disable tracking just before calling Affise.forget:

```kotlin
Affise.settings(affiseAppId, secretKey).start(context) // Start Affise SDK

Affise.setTrackingEnabled(false)
Affise.forget() // to forget users data
```

## Get referrer

Use the next public method of SDK

> To get Install referrer by installing from RuStore include module [`RuStore`](#modules)

> To get Install referrer by installing from AppGallery include module [`Huawei`](#modules)

For kotlin:

```kotlin
Affise.getReferrerUrl { referrer ->
  // handle referrer
}
```

For java:

```java
Affise.getReferrerUrl(referrer -> {
    // handle referrer
});
```

## Get referrer parameter

Use the next public method of SDK to get referrer parameter by

> To get Install referrer by installing from RuStore include module [`RuStore`](#modules)

> To get Install referrer by installing from AppGallery include module [`Huawei`](#modules)

For kotlin:

```kotlin
Affise.getReferrerUrlValue(ReferrerKey.CLICK_ID) { value ->
  // handle referrer value
}
```

For java:

```java
Affise.getReferrerUrlValue(ReferrerKey.CLICK_ID, value -> {
    // handle referrer value
});
```

## Referrer keys

In examples above `ReferrerKey.CLICK_ID` is used, but many others is available:

- `AD_ID`
- `CAMPAIGN_ID`
- `CLICK_ID`
- `AFFISE_AD`
- `AFFISE_AD_ID`
- `AFFISE_AD_TYPE`
- `AFFISE_ADSET`
- `AFFISE_ADSET_ID`
- `AFFISE_AFFC_ID`
- `AFFISE_CHANNEL`
- `AFFISE_CLICK_LOOK_BACK`
- `AFFISE_COST_CURRENCY`
- `AFFISE_COST_MODEL`
- `AFFISE_COST_VALUE`
- `AFFISE_DEEPLINK`
- `AFFISE_KEYWORDS`
- `AFFISE_MEDIA_TYPE`
- `AFFISE_MODEL`
- `AFFISE_OS`
- `AFFISE_PARTNER`
- `AFFISE_REF`
- `AFFISE_SITE_ID`
- `AFFISE_SUB_SITE_ID`
- `AFFISE_SUB_1`
- `AFFISE_SUB_2`
- `AFFISE_SUB_3`
- `AFFISE_SUB_4`
- `AFFISE_SUB_5`
- `AFFC`
- `PID`
- `SUB_1`
- `SUB_2`
- `SUB_3`
- `SUB_4`
- `SUB_5`

## Get module state

> **Warning**
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
>
> Required [Module Status](#module-status)
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

Get state of the module:

For kotlin:

```kotlin
Affise.Module.getStatus(AffiseModules.Status) { response ->
  // handle response
}
```

For java:

```java
Affise.Module.getStatus(AffiseModules.Status, response -> {
    // handle response
});
```

## Get random user Id

Use the next public method of SDK

For kotlin:

```kotlin
Affise.getRandomUserId()
```

## Get random device Id

> **Note**
>
> Use [Affise AndroidId Module](#modules) to make `device id` more persistent on application reinstall

Use the next public method of SDK

For kotlin:

```kotlin
Affise.getRandomDeviceId()
```

## Get providers

Returns providers map with [ProviderType](#providertype-identifiers-collection) as key

```kotlin
val providers: Map<ProviderType, Any?> = Affise.getProviders()
val key = ProviderType.AFFISE_APP_TOKEN
val value = providers[key]
```

## Is first run

Use the next public method of SDK

For kotlin:

```kotlin
Affise.isFirstRun()
```

## WebView tracking

### Initialize WebView

To integrate the library into the JavaScript environment, we added a bridge between JavaScript and the native SDK. Now you can send events and use the functionality of the native library directly from WebView.
Here are step by step instructions:

```kotlin
// retrieve WebView from view hierarchy
val webView = findViewById<WebView>(R.Id.your_webview_id)
// make sure javascript is enabled
webView.javaScriptEnabled = true
// initialize WebView with Affise native library
Affise.registerWebView(webView)
```

Other Javascript environment features is described below.

### Events tracking JS

> Demo app [index.html](app/src/main/assets/index.html)

After WebView is initialized you send events from JavaScript environment

```javascript
let data = { card: 4138, type: 'phone' };
new AddPaymentInfoEvent({
  userData: 'taxi',
})
  .addPredefinedParameter(PredefinedString.PURCHASE_CURRENCY, 'USD')
  .addPredefinedParameter(PredefinedObject.CONTENT, data)
  .addPredefinedParameter(PredefinedFloat.PRICE, 2.19)
  .send(); // Send event
```

Just like with native SDK, javascript environment also provides default events that can be passed from WebView:

- `AchieveLevelEvent`
- `AddPaymentInfoEvent`
- `AddToCartEvent`
- `AddToWishlistEvent`
- `AdRevenueEvent`
- `ClickAdvEvent`
- `CompleteRegistrationEvent`
- `CompleteStreamEvent`
- `CompleteTrialEvent`
- `CompleteTutorialEvent`
- `ContactEvent`
- `ContentItemsViewEvent`
- `CustomId01Event`
- `CustomId02Event`
- `CustomId03Event`
- `CustomId04Event`
- `CustomId05Event`
- `CustomId06Event`
- `CustomId07Event`
- `CustomId08Event`
- `CustomId09Event`
- `CustomId10Event`
- `CustomizeProductEvent`
- `DeepLinkedEvent`
- `DonateEvent`
- `FindLocationEvent`
- `InitiateCheckoutEvent`
- `InitiatePurchaseEvent`
- `InitiateStreamEvent`
- `InviteEvent`
- `LastAttributedTouchEvent`
- `LeadEvent`
- `ListViewEvent`
- `LoginEvent`
- `OpenedFromPushNotificationEvent`
- `OrderEvent`
- `OrderItemAddedEvent`
- `OrderItemRemoveEvent`
- `OrderCancelEvent`
- `OrderReturnRequestEvent`
- `OrderReturnRequestCancelEvent`
- `PurchaseEvent`
- `RateEvent`
- `ReEngageEvent`
- `ReserveEvent`
- `SalesEvent`
- `ScheduleEvent`
- `SearchEvent`
- `ShareEvent`
- `SpendCreditsEvent`
- `StartRegistrationEvent`
- `StartTrialEvent`
- `StartTutorialEvent`
- `SubmitApplicationEvent`
- `SubscribeEvent`
- `TravelBookingEvent`
- `UnlockAchievementEvent`
- `UnsubscribeEvent`
- `UpdateEvent`
- `ViewAdvEvent`
- `ViewCartEvent`
- `ViewContentEvent`
- `ViewItemEvent`
- `ViewItemsEvent`
- `InitialSubscriptionEvent`
- `InitialTrialEvent`
- `InitialOfferEvent`
- `ConvertedTrialEvent`
- `ConvertedOfferEvent`
- `TrialInRetryEvent`
- `OfferInRetryEvent`
- `SubscriptionInRetryEvent`
- `RenewedSubscriptionEvent`
- `FailedSubscriptionFromRetryEvent`
- `FailedOfferFromRetryEvent`
- `FailedTrialFromRetryEvent`
- `FailedSubscriptionEvent`
- `FailedOfferiseEvent`
- `FailedTrialEvent`
- `ReactivatedSubscriptionEvent`
- `RenewedSubscriptionFromRetryEvent`
- `ConvertedOfferFromRetryEvent`
- `ConvertedTrialFromRetryEvent`
- `UnsubscriptionEvent`

### Predefined event parameters JS

Each event can be extended with custom event parameters. By calling `addPredefinedParameter` function you can pass [predefined parameters](#predefinedstring)

For example:

```javascript
let event = ...

event
  .addPredefinedParameter(PredefinedString.PURCHASE_CURRENCY, 'USD')
  .addPredefinedParameter(PredefinedFloat.PRICE, 2.19)
  .addPredefinedParameter(PredefinedLong.QUANTITY, 1)
  .addPredefinedParameter(PredefinedObject.CONTENT, { card: 4138, type: 'phone' })
  .addPredefinedParameter(PredefinedListObject.CONTENT_LIST, [{content:'songs'}, {content:'videos'}])
  .send(); // Send event
```

### Custom events JS

If above event functionality still limits your use case, you can always extend `Event` class to override fields you are missing

```javascript
class MyCustomEvent extends Event {
    constructor(args) {
        super('MyCustom', args)
    }
}
```

## Custom

### ConversionId

Adds 3 PredefinedString values: `PredefinedString.CONVERSION_ID`, `PredefinedString.ORDER_ID`, `PredefinedString.PRODUCT_ID`

> `CONVERSION_ID` = `ORDER_ID`_`PRODUCT_ID`

```kotlin
val event = AddToCartEvent()

val conversionId = event.customPredefined().conversionId("ORDER_ID", "PRODUCT_ID")

event.send() // Send event
```

# SDK to SDK integrations

## AdMob

For more information how to setup, please check [official docs](https://developers.google.com/admob/android/impression-level-ad-revenue)

```kotlin
var rewardedAd: RewardedAd? = null
val adRequest = AdRequest.Builder().build()
RewardedAd.load(this,"AD_UNIT_ID", adRequest, object : RewardedAdLoadCallback() {
    override fun onAdLoaded(ad: RewardedAd) {
        rewardedAd = ad
        // Set paid event listener
        rewardedAd.onPaidEventListener = OnPaidEventListener { adValue ->
            val loadedAdapterResponseInfo = ad.responseInfo.loadedAdapterResponse

            // Send AdRevenue info
            AffiseAdRevenue(AffiseAdSource.ADMOB)
                .setRevenue(adValue.valueMicros / 1000000, adValue.currencyCode)
                .setNetwork(loadedAdapterResponseInfo.adSourceName)
                .setUnit(ad.adUnitId)
                .send()
        }
    }
})
```

## AppLovin MAX

For more information how to setup, please check [official docs](https://dash.applovin.com/documentation/mediation/android/getting-started/advanced-settings)

```kotlin
override fun onAdRevenuePaid(ad: MaxAd)
{
    // Send AdRevenue info
    AffiseAdRevenue(AffiseAdSource.APPLOVIN_MAX)
      .setRevenue(ad.revenue, "USD")
      .setNetwork(ad.networkName)
      .setUnit(ad.adUnitId)
      .setPlacement(ad.placement)
      .send()
}
```

## Helium by Chartboost

For more information how to setup, please check [official docs](https://developers.chartboost.com/docs/mediation-android-configure-helium#implementation)

```kotlin
val ilrdObserver = object: HeliumIlrdObserver {
    override fun onImpression(impData: HeliumImpressionData) {
        val json: JSONObject = impData.ilrdInfo

        val revenue = json.getDouble("ad_revenue")
        val currency = json.getString("currency_type")

        // Send AdRevenue info
        AffiseAdRevenue(AffiseAdSource.HELIUM_CHARTBOOST)
            .setRevenue(revenue, currency)
            .setNetwork(json.optString("network_name"))
            .setUnit(json.optString("placement_name"))
            .setPlacement(json.optString("line_item_name"))
            .send()
    }
}
```

## ironSource

For more information how to setup, please check [official docs](https://developers.is.com/ironsource-mobile/android/ad-revenue-measurement-integration/#step-2)

```kotlin
fun onImpressionSuccess(impressionData: ImpressionData) {
    // Send AdRevenue info
    AffiseAdRevenue(AffiseAdSource.IRONSOURCE)
        .setRevenue(impressionData.revenue, "USD")
        .setNetwork(impressionData.adNetwork)
        .setUnit(impressionData.adUnit)
        .setPlacement(impressionData.placement)
        .send()
}
```

## Admost

For more information how to setup, please check [official docs](https://admost.github.io/amrandroid/)

```kotlin
fun onAdRevenuePaid(impressionData: AdMostImpressionData) {
    // Send AdRevenue info
    AffiseAdRevenue(AffiseAdSource.ADMOST)
        .setRevenue(impressionData.Revenue, impressionData.Currency)
        .setNetwork(impressionData.Network)
        .setUnit(impressionData.AdUnitId)
        .setPlacement(impressionData.PlacementId)
        .send()
}
```

# Debug

## Validate credentials

> **Warning**
> 
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥
> 
> Debug methods WON'T work on Production
>
> 🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥🟥

Validate your credentials by receiving `ValidationStatus` values:

- `VALID` - your credentials are valid
- `INVALID_APP_ID` - your app id is not valid
- `INVALID_SECRET_KEY` - your SDK secretKey is not valid
- `PACKAGE_NAME_NOT_FOUND` - your application package name not found
- `NOT_WORKING_ON_PRODUCTION` - you using debug method on production
- `NETWORK_ERROR` - network or server not available (for example `Airoplane mode` is active)

```kotlin
Affise
  .settings(
    affiseAppId = "Your appId",
    secretKey = "Your SDK secretKey"
  )
  .setProduction(false) //To enable debug methods set Production to false
  .start(this) // Start Affise SDK

Affise.Debug.validate { status ->
    // Handle validation status
}
```


## Version

Get Affise library version

```kotlin
Affise.Debug.version()
```