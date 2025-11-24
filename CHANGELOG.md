# Changelog

## [1.6.67] - 2025-11-24

### Fixed

- Fix `AffiseError` parsing
- 
## [1.6.66] - 2025-09-29

### Added

- Error `SDK is already initialized` to `Affise.settings.setOnInitError`

## [1.6.65] - 2025-09-05

### Added

- Api `Affise.settings.setDisableModules`

### Removed

- Api `Affise.Module.moduleStart`

## [1.6.64] - 2025-08-26

### Fixed

- Fix module `TikTok` for crossplatform

## [1.6.63] - 2025-08-14

### Fixed

- Fix module `TikTok`

## [1.6.62] - 2025-08-06

### Added

- New module `TikTok`
- Api `Affise.Module.TikTok.sendEvent`

## [1.6.61] - 2025-07-29

### Fixed

- Fix android API < 24 crash for cross platform

## [1.6.60] - 2025-07-03

### Added

- Provider `PUSHTOKEN_SERVICE`

### Fixed

- Fix `CI/CD` `sonatype` repository

## [1.6.59] - 2025-06-16

### Added

- Api `Affise.Module.AppsFlyer.hasModule`
- Api `Affise.Module.Link.hasModule`
- Api `Affise.Module.Subscription.hasModule`

### Changed

- Api `Affise.Module.linkResolve` moved to `Affise.Module.Link.resolve`
- Api `Affise.Module.fetchProducts` moved to `Affise.Module.Subscription.fetchProducts`
- Api `Affise.Module.purchase` moved to `Affise.Module.Subscription.purchase`

## [1.6.58] - 2025-05-15

### Added

- New module `AppsFlyer`
- Api `Affise.Module.AppsFlyer.logEvent`

## [1.6.57] - 2025-05-02

### Added

- New module `meta` for `Facebook`
- Provider for `Facebook` install referrer

## [1.6.56] - 2025-04-09

### Changed

- Update `OAID` to use `com.huawei.hms:ads-identifier`
- Provider api `OAID` moved to module `huawei`
- Provider api `OAID_MD5` moved to module `huawei`

## [1.6.55] - 2025-03-21

### Added

- Api `Affise.settings.setOnInitSuccess`
- Api `Affise.settings.setOnInitError`
- Api `Affise.Debug.version`

## [1.6.54] - 2025-03-11

### Changed

- Update api `Affise.getReferrerOnServer` moved to `Affise.getDeferredDeeplink`
- Update api `Affise.getReferrerOnServerValue` moved to `Affise.getDeferredDeeplinkValue`

## [1.6.53] - 2025-02-21

### Added

- Persistent `AFFISE_DEVICE_ID`

## [1.6.52] - 2025-02-04

### Added

- New event index `affise_event_id_index`
- New postback index `uuid_index`

### Fixed

- Fix `ProviderType.INSTALL_FIRST_EVENT`

## [1.6.51] - 2025-01-20

### Added

- Api `Affise.getReferrerOnServer`
- Api `Affise.getReferrerOnServerValue`

## [1.6.50] - 2024-12-12

### Added

- New module `huawei` for AppGallery install referrer

## [1.6.49] - 2024-11-25

### Fixed

- Fix event writing to storage exception

## [1.6.48] - 2024-11-21

### Added

- New module `RuStore`

## [1.6.47] - 2024-10-21

### Fixed

- Fix `Internal` library for crossplatform

## [1.6.46] - 2024-10-21

### Fixed

- Fix CPU overhead for activity listeners

### Removed

- Api `setEnabledMetrics`
- Api `setAutoCatchingTypes`

## [1.6.45] - 2024-10-03

### Fixed

- Fix `Affise.Module.getStatus` timing retry

## [1.6.44] - 2024-09-04

### Fixed

- Fix `Gaid` in `Advertising` module

## [1.6.43] - 2024-09-03

### Fixed

- Module `Subscription`

### Changed

- Update `internal` library for crossplatform

## [1.6.42] - 2024-08-19

### Changed

- Api `Affise.getReferrer` to `Affise.getReferrerUrl`
- Api `Affise.getReferrerValue` to `Affise.getReferrerUrlValue`

## [1.6.41] - 2024-08-14

### Added

- New internal events

## [1.6.40] - 2024-07-29

### Fixed

- Api `Affise.Module.getStatus`

## [1.6.39] - 2024-07-22

### Added

- Modules compatibility check

## [1.6.38] - 2024-07-03

### Changed

- Update `kotlin` version `1.8.22`

### Fixed

- AGP error for `crossplatform`
- Event timestamp field for `crossplatform`

## [1.6.37] - 2024-06-24

### Added

- New module `Link`

### Changed

- Update api `Affise.getStatus` moved to `Affise.Module.getStatus`
- Update api `Affise.moduleStart` moved to `Affise.Module.moduleStart`
- Update api `Affise.getModulesInstalled` moved to `Affise.Module.getModulesInstalled`

## [1.6.36] - 2024-06-19

### Changed

- Update `registerDeeplinkCallback` change uri to convenient values
- Update `registerDeeplinkCallback` return removed

## [1.6.35] - 2024-06-13

### Changed

- Update `Demo app`
- Update modules extensions

## [1.6.34] - 2024-06-03

### Changed

- Update `internal` library for `crossplatform`

## [1.6.33] - 2024-05-29

### Removed

- Result for failed `sendNow`

### Changed

- Update `kotlin` version `1.6`
- Update each modules for separate `kotlin` version
- Update `internal` library for `crossplatform`
- Update `gradle-wrapper` version `7.5`

## [1.6.32] - 2024-04-26

### Fixed

- CI/CD

## [1.6.31] - 2024-04-26

### Added

- New module `AndroidId`

### Changed

- Fix typos in `Subscription` module

## [1.6.30] - 2024-04-19

### Added

- Event api `sendNow`

### Removed

- Api `sendEvents`

### Changed

- Fix ExecutorServiceProvider

## [1.6.29] - 2024-04-16

### Added

- New module `Subscription`
- New event `FailedPurchaseEvent`
- New values in `PredefinedString`

### Changed

- Improve `AffiseModuleManager`
- Update gradle for demo app

[1.6.67]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.66...v1.6.67
[1.6.66]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.65...v1.6.66
[1.6.65]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.64...v1.6.65
[1.6.64]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.63...v1.6.64
[1.6.63]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.62...v1.6.63
[1.6.62]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.61...v1.6.62
[1.6.61]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.60...v1.6.61
[1.6.60]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.59...v1.6.60
[1.6.59]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.58...v1.6.59
[1.6.58]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.57...v1.6.58
[1.6.57]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.56...v1.6.57
[1.6.56]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.55...v1.6.56
[1.6.55]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.54...v1.6.55
[1.6.54]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.53...v1.6.54
[1.6.53]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.52...v1.6.53
[1.6.52]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.51...v1.6.52
[1.6.51]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.50...v1.6.51
[1.6.50]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.49...v1.6.50
[1.6.49]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.48...v1.6.49
[1.6.48]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.47...v1.6.48
[1.6.47]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.46...v1.6.47
[1.6.46]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.45...v1.6.46
[1.6.45]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.44...v1.6.45
[1.6.44]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.43...v1.6.44
[1.6.43]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.42...v1.6.43
[1.6.42]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.41...v1.6.42
[1.6.41]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.40...v1.6.41
[1.6.40]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.39...v1.6.40
[1.6.39]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.38...v1.6.39
[1.6.38]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.37...v1.6.38
[1.6.37]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.36...v1.6.37
[1.6.36]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.35...v1.6.36
[1.6.35]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.34...v1.6.35
[1.6.34]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.33...v1.6.34
[1.6.33]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.32...v1.6.33
[1.6.32]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.31...v1.6.32
[1.6.31]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.30...v1.6.31
[1.6.30]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.29...v1.6.30
[1.6.29]: https://github.com/affise/affise-mmp-sdk-android/compare/v1.6.28...v1.6.29
