package com.affise.app.ui.factories

import com.affise.app.ui.utils.toTimestamp
import com.affise.attribution.ad.AffiseAdSource
import com.affise.attribution.events.Event
import com.affise.attribution.events.parameters.PredefinedFloat
import com.affise.attribution.events.parameters.PredefinedListObject
import com.affise.attribution.events.parameters.PredefinedLong
import com.affise.attribution.events.parameters.PredefinedObject
import com.affise.attribution.events.parameters.PredefinedString
import com.affise.attribution.events.predefined.AchieveLevelEvent
import com.affise.attribution.events.predefined.AdRevenueEvent
import com.affise.attribution.events.predefined.AddPaymentInfoEvent
import com.affise.attribution.events.predefined.AddToCartEvent
import com.affise.attribution.events.predefined.AddToWishlistEvent
import com.affise.attribution.events.predefined.ClickAdvEvent
import com.affise.attribution.events.predefined.CompleteRegistrationEvent
import com.affise.attribution.events.predefined.CompleteStreamEvent
import com.affise.attribution.events.predefined.CompleteTrialEvent
import com.affise.attribution.events.predefined.CompleteTutorialEvent
import com.affise.attribution.events.predefined.ContactEvent
import com.affise.attribution.events.predefined.ContentItemsViewEvent
import com.affise.attribution.events.predefined.CustomId01Event
import com.affise.attribution.events.predefined.CustomId02Event
import com.affise.attribution.events.predefined.CustomId03Event
import com.affise.attribution.events.predefined.CustomId04Event
import com.affise.attribution.events.predefined.CustomId05Event
import com.affise.attribution.events.predefined.CustomId06Event
import com.affise.attribution.events.predefined.CustomId07Event
import com.affise.attribution.events.predefined.CustomId08Event
import com.affise.attribution.events.predefined.CustomId09Event
import com.affise.attribution.events.predefined.CustomId10Event
import com.affise.attribution.events.predefined.CustomizeProductEvent
import com.affise.attribution.events.predefined.DeepLinkedEvent
import com.affise.attribution.events.predefined.DonateEvent
import com.affise.attribution.events.predefined.FindLocationEvent
import com.affise.attribution.events.predefined.InitiateCheckoutEvent
import com.affise.attribution.events.predefined.InitiatePurchaseEvent
import com.affise.attribution.events.predefined.InitiateStreamEvent
import com.affise.attribution.events.predefined.InviteEvent
import com.affise.attribution.events.predefined.LastAttributedTouchEvent
import com.affise.attribution.events.predefined.LeadEvent
import com.affise.attribution.events.predefined.ListViewEvent
import com.affise.attribution.events.predefined.LoginEvent
import com.affise.attribution.events.predefined.OpenedFromPushNotificationEvent
import com.affise.attribution.events.predefined.OrderCancelEvent
import com.affise.attribution.events.predefined.OrderEvent
import com.affise.attribution.events.predefined.OrderItemAddedEvent
import com.affise.attribution.events.predefined.OrderItemRemoveEvent
import com.affise.attribution.events.predefined.OrderReturnRequestCancelEvent
import com.affise.attribution.events.predefined.OrderReturnRequestEvent
import com.affise.attribution.events.predefined.PurchaseEvent
import com.affise.attribution.events.predefined.RateEvent
import com.affise.attribution.events.predefined.ReEngageEvent
import com.affise.attribution.events.predefined.ReserveEvent
import com.affise.attribution.events.predefined.SalesEvent
import com.affise.attribution.events.predefined.ScheduleEvent
import com.affise.attribution.events.predefined.SearchEvent
import com.affise.attribution.events.predefined.ShareEvent
import com.affise.attribution.events.predefined.SpendCreditsEvent
import com.affise.attribution.events.predefined.StartRegistrationEvent
import com.affise.attribution.events.predefined.StartTrialEvent
import com.affise.attribution.events.predefined.StartTutorialEvent
import com.affise.attribution.events.predefined.SubmitApplicationEvent
import com.affise.attribution.events.predefined.SubscribeEvent
import com.affise.attribution.events.predefined.TravelBookingEvent
import com.affise.attribution.events.predefined.UnlockAchievementEvent
import com.affise.attribution.events.predefined.UnsubscribeEvent
import com.affise.attribution.events.predefined.UpdateEvent
import com.affise.attribution.events.predefined.ViewAdvEvent
import com.affise.attribution.events.predefined.ViewCartEvent
import com.affise.attribution.events.predefined.ViewContentEvent
import com.affise.attribution.events.predefined.ViewItemEvent
import com.affise.attribution.events.predefined.ViewItemsEvent
import com.affise.attribution.events.subscription.ConvertedOfferEvent
import com.affise.attribution.events.subscription.ConvertedOfferFromRetryEvent
import com.affise.attribution.events.subscription.ConvertedTrialEvent
import com.affise.attribution.events.subscription.ConvertedTrialFromRetryEvent
import com.affise.attribution.events.subscription.FailedOfferFromRetryEvent
import com.affise.attribution.events.subscription.FailedOfferiseEvent
import com.affise.attribution.events.subscription.FailedSubscriptionEvent
import com.affise.attribution.events.subscription.FailedSubscriptionFromRetryEvent
import com.affise.attribution.events.subscription.FailedTrialEvent
import com.affise.attribution.events.subscription.FailedTrialFromRetryEvent
import com.affise.attribution.events.subscription.InitialOfferEvent
import com.affise.attribution.events.subscription.InitialSubscriptionEvent
import com.affise.attribution.events.subscription.InitialTrialEvent
import com.affise.attribution.events.subscription.OfferInRetryEvent
import com.affise.attribution.events.subscription.ReactivatedSubscriptionEvent
import com.affise.attribution.events.subscription.RenewedSubscriptionEvent
import com.affise.attribution.events.subscription.RenewedSubscriptionFromRetryEvent
import com.affise.attribution.events.subscription.SubscriptionInRetryEvent
import com.affise.attribution.events.subscription.TrialInRetryEvent
import com.affise.attribution.events.subscription.UnsubscriptionEvent
import org.json.JSONObject

class DefaultEventsFactory : EventsFactory {
    override fun createEvents(): List<Event> {
        return listOf(
            createAchieveLevelEvent(),
            createAddPaymentInfoEvent(),
            createAddToCartEvent(),
            createAddToWishlistEvent(),
            createAdRevenueEvent(),
            createClickAdvEvent(),
            createCompleteRegistrationEvent(),
            createCompleteStreamEvent(),
            createCompleteTrialEvent(),
            createCompleteTutorialEvent(),
            createContactEvent(),
            createContentItemsViewEvent(),
            createCustomId01Event(),
            createCustomId02Event(),
            createCustomId03Event(),
            createCustomId04Event(),
            createCustomId05Event(),
            createCustomId06Event(),
            createCustomId07Event(),
            createCustomId08Event(),
            createCustomId09Event(),
            createCustomId10Event(),
            createCustomizeProductEvent(),
            createDeepLinkedEvent(),
            createDonateEvent(),
            createFindLocationEvent(),
            createInitiateCheckoutEvent(),
            createInitiatePurchaseEvent(),
            createInitiateStreamEvent(),
            createInviteEvent(),
            createLastAttributedTouchEvent(),
            createLeadEvent(),
            createListViewEvent(),
            createLoginEvent(),
            createOpenedFromPushNotificationEvent(),
            createOrderEvent(),
            createOrderItemAddedEvent(),
            createOrderItemRemoveEvent(),
            createOrderCancelEvent(),
            createOrderReturnRequestEvent(),
            createOrderReturnRequestCancelEvent(),
            createPurchaseEvent(),
            createRateEvent(),
            createReEngageEvent(),
            createReserveEvent(),
            createSalesEvent(),
            createScheduleEvent(),
            createSearchEvent(),
            createShareEvent(),
            createSpendCreditsEvent(),
            createStartRegistrationEvent(),
            createStartTrialEvent(),
            createStartTutorialEvent(),
            createSubmitApplicationEvent(),
            createSubscribeEvent(),
            createTravelBookingEvent(),
            createUnlockAchievementEvent(),
            createUnsubscribeEvent(),
            createUpdateEvent(),
            createViewAdvEvent(),
            createViewCartEvent(),
            createViewContentEvent(),
            createViewItemEvent(),
            createViewItemsEvent(),
            createInitialSubscriptionEvent(),
            createInitialTrialEvent(),
            createInitialOfferEvent(),
            createConvertedTrialEvent(),
            createConvertedOfferEvent(),
            createTrialInRetryEvent(),
            createOfferInRetryEvent(),
            createSubscriptionInRetryEvent(),
            createRenewedSubscriptionEvent(),
            createFailedSubscriptionFromRetryEvent(),
            createFailedOfferFromRetryEvent(),
            createFailedTrialFromRetryEvent(),
            createFailedSubscriptionEvent(),
            createFailedOfferiseEvent(),
            createFailedTrialEvent(),
            createReactivatedSubscriptionEvent(),
            createRenewedSubscriptionFromRetryEvent(),
            createConvertedOfferFromRetryEvent(),
            createConvertedTrialFromRetryEvent(),
            createUnsubscriptionEvent()
        )
    }

    private fun createAchieveLevelEvent(): Event {
        return AchieveLevelEvent("warlock")
            .addPredefinedParameter(PredefinedString.DEEP_LINK, "https://new-game.lt")
            .addPredefinedParameter(PredefinedLong.SCORE, 25013L)
            .addPredefinedParameter(PredefinedLong.LEVEL, 70L)
            .addPredefinedParameter(PredefinedString.SUCCESS, "true")
            .addPredefinedParameter(PredefinedString.TUTORIAL_ID, "12")
    }

    private fun createAddPaymentInfoEvent(): Event {
        return AddPaymentInfoEvent("taxi")
            .addPredefinedParameter(PredefinedString.PURCHASE_CURRENCY, "USD")
    }

    private fun createAddToCartEvent(): Event {
        return AddToCartEvent("milk, cookies, meat, vegetables")
            .addPredefinedParameter(PredefinedString.DESCRIPTION, "best before 2029")
    }

    private fun createAddToWishlistEvent(): Event {
        return AddToWishlistEvent("next year")
            .addPredefinedParameter(PredefinedString.COUNTRY, "Russia")
            .addPredefinedParameter(PredefinedString.CITY, "Voronezh")
            .addPredefinedParameter(PredefinedFloat.LAT, 42.0f)
            .addPredefinedParameter(PredefinedFloat.LONG, 24.0f)
    }

    private fun createAdRevenueEvent(): Event {
        return AdRevenueEvent("next year")
            .addPredefinedParameter(PredefinedString.SOURCE, AffiseAdSource.ADMOB.type)
            .addPredefinedParameter(PredefinedFloat.REVENUE, 42.0f)
            .addPredefinedParameter(PredefinedString.CURRENCY, "USD")
            .addPredefinedParameter(PredefinedString.NETWORK, "test")
            .addPredefinedParameter(PredefinedString.UNIT, "m")
            .addPredefinedParameter(PredefinedString.PLACEMENT, "end")
    }

    private fun createClickAdvEvent(): Event {
        return ClickAdvEvent("header")
            .addPredefinedParameter(PredefinedString.PARAM_01, "PARAM_01")
            .addPredefinedParameter(PredefinedString.PARAM_02, "PARAM_02")
            .addPredefinedParameter(PredefinedString.PARAM_03, "PARAM_03")
            .addPredefinedParameter(PredefinedString.PARAM_04, "PARAM_04")
            .addPredefinedParameter(PredefinedString.PARAM_05, "PARAM_05")
            .addPredefinedParameter(PredefinedString.PARAM_06, "PARAM_06")
            .addPredefinedParameter(PredefinedString.PARAM_07, "PARAM_07")
            .addPredefinedParameter(PredefinedString.PARAM_08, "PARAM_08")
            .addPredefinedParameter(PredefinedString.PARAM_09, "PARAM_09")
            .addPredefinedParameter(PredefinedString.PARAM_10, "PARAM_10")
    }

    private fun createCompleteRegistrationEvent(): Event {
        return CompleteRegistrationEvent("promo")
            .addPredefinedParameter(PredefinedString.VALIDATED, "02.11.2021")
            .addPredefinedParameter(PredefinedString.REVIEW_TEXT, "approve")
            .addPredefinedParameter(PredefinedString.CUSTOMER_SEGMENT, "DOG")
    }

    private fun createCompleteStreamEvent(): Event {
        return CompleteStreamEvent("23 hours")
            .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)
    }

    private fun createCompleteTrialEvent(): Event {
        return CompleteTrialEvent("time")
            .addPredefinedParameter(PredefinedString.REGISTRATION_METHOD, "SMS")
    }

    private fun createCompleteTutorialEvent(): Event {
        return CompleteTutorialEvent("intro")
            .addPredefinedParameter(PredefinedString.REGISTRATION_METHOD, "SMS")
    }

    private fun createContactEvent(): Event {
        return ContactEvent("contact")
            .addPredefinedParameter(PredefinedString.REGISTRATION_METHOD, "SMS")
    }

    private fun createContentItemsViewEvent(): Event {
        return ContentItemsViewEvent("personal")
            .addPredefinedParameter(PredefinedObject.CONTENT, JSONObject().apply {
                put("collection", "Greatest Hits")
            })
            .addPredefinedParameter(PredefinedString.CONTENT_ID, "2561")
            .addPredefinedParameter(
                PredefinedListObject.CONTENT_LIST, listOf(
                JSONObject().apply {
                    put("content", "songs, videos")
                }
            ))
            .addPredefinedParameter(PredefinedString.CONTENT_TYPE, "MATURE")
            .addPredefinedParameter(PredefinedString.CURRENCY, "USD")
    }

    private fun createCustomId01Event(): Event {
        return CustomId01Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId02Event(): Event {
        return CustomId02Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId03Event(): Event {
        return CustomId03Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId04Event(): Event {
        return CustomId04Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId05Event(): Event {
        return CustomId05Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId06Event(): Event {
        return CustomId06Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId07Event(): Event {
        return CustomId07Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId08Event(): Event {
        return CustomId08Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId09Event(): Event {
        return CustomId09Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomId10Event(): Event {
        return CustomId10Event("custom")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createCustomizeProductEvent(): Event {
        return CustomizeProductEvent("Customize")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createDeepLinkedEvent(): Event {
        return DeepLinkedEvent("referrer: google")
            .addPredefinedParameter(PredefinedString.ADREV_AD_TYPE, "interstitial")
            .addPredefinedParameter(PredefinedString.REGION, "ASIA")
            .addPredefinedParameter(PredefinedString.CLASS, "student")
    }

    private fun createDonateEvent(): Event {
        return DonateEvent("donate")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createFindLocationEvent(): Event {
        return FindLocationEvent("location")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createInitiateCheckoutEvent(): Event {
        return InitiateCheckoutEvent("checkout")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createInitiatePurchaseEvent(): Event {
        return InitiatePurchaseEvent("best price")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createInitiateStreamEvent(): Event {
        return InitiateStreamEvent("shooter")
            .addPredefinedParameter(PredefinedString.COUPON_CODE, "25XLKM")
            .addPredefinedParameter(PredefinedString.VIRTUAL_CURRENCY_NAME, "BTC")
    }

    private fun createInviteEvent(): Event {
        return InviteEvent("dancing")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createLastAttributedTouchEvent(): Event {
        return LastAttributedTouchEvent("tablet")
            .addPredefinedParameter(PredefinedString.SUBSCRIPTION_ID, "lasAK22")
    }

    private fun createLeadEvent(): Event {
        return LeadEvent("lead")
            .addPredefinedParameter(PredefinedString.PAYMENT_INFO_AVAILABLE, "card")
            .addPredefinedParameter(PredefinedString.SEARCH_STRING, "best car wash")
    }

    private fun createListViewEvent(): Event {
        return ListViewEvent("items")
            .addPredefinedParameter(PredefinedString.PAYMENT_INFO_AVAILABLE, "card")
            .addPredefinedParameter(PredefinedString.SEARCH_STRING, "best car wash")
            .addPredefinedParameter(PredefinedString.SUGGESTED_DESTINATIONS, "crete, spain")
            .addPredefinedParameter(PredefinedString.SUGGESTED_HOTELS, "beach resort, marina spa")
    }

    private fun createLoginEvent(): Event {
        return LoginEvent("web")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createOpenedFromPushNotificationEvent(): Event {
        return OpenedFromPushNotificationEvent("active")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createOrderEvent(): Event {
        return OrderEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)

        // PredefinedGroup example
//            .addPredefinedListGroup(
//                listOf(
//                    PredefinedGroup()
//                        .addPredefinedParameter(PredefinedString.CUSTOMER_USER_ID,"KDCJHB10834rJHG")
//                        .addPredefinedParameter(PredefinedString.CONTENT_ID, "334923062984")
//                        .addPredefinedParameter(PredefinedString.DESCRIPTION,"SevenFriday Men's M1B-01 Urban Explorer 47.6 Automatic Watch"                        )
//                        .addPredefinedParameter(PredefinedLong.QUANTITY, 3)
//                        .addPredefinedParameter(PredefinedFloat.PRICE, 499f)
//                        .addPredefinedParameter(PredefinedString.CURRENCY, "USD")
//                        .addPredefinedParameter(PredefinedFloat.REVENUE, 1497f)
//                        .addPredefinedParameter(PredefinedString.ORDER_ID, "ID_34923"),
//
//                    PredefinedGroup()
//                        .addPredefinedParameter(PredefinedString.CUSTOMER_USER_ID,"KDCJHB10834rJHG")
//                        .addPredefinedParameter(PredefinedString.CONTENT_ID, "383791923777")
//                        .addPredefinedParameter(PredefinedString.DESCRIPTION,"2021 Apple iPad 9th Gen 64/256GB WiFi 10.2"                        )
//                        .addPredefinedParameter(PredefinedLong.QUANTITY, 1)
//                        .addPredefinedParameter(PredefinedFloat.PRICE, 299f)
//                        .addPredefinedParameter(PredefinedString.CURRENCY, "USD")
//                        .addPredefinedParameter(PredefinedFloat.REVENUE, 299f)
//                        .addPredefinedParameter(PredefinedString.ORDER_ID, "ID_83792")
//                )
//            )
    }

    private fun createOrderItemAddedEvent(): Event {
        return OrderItemAddedEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createOrderItemRemoveEvent(): Event {
        return OrderItemRemoveEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createOrderCancelEvent(): Event {
        return OrderCancelEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createOrderReturnRequestEvent(): Event {
        return OrderReturnRequestEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createOrderReturnRequestCancelEvent(): Event {
        return OrderReturnRequestCancelEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createPurchaseEvent(): Event {
        return PurchaseEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createRateEvent(): Event {
        return RateEvent("no bugs")
            .addPredefinedParameter(PredefinedString.PREFERRED_NEIGHBORHOODS, "2")
            .addPredefinedParameter(PredefinedLong.PREFERRED_NUM_STOPS, 4L)
            .addPredefinedParameter(PredefinedFloat.PREFERRED_PRICE_RANGE, 10.22f)
            .addPredefinedParameter(PredefinedLong.PREFERRED_STAR_RATINGS, 6L)
    }

    private fun createReEngageEvent(): Event {
        return ReEngageEvent("web")
            .addPredefinedParameter(PredefinedString.CUSTOMER_USER_ID, "4")
    }

    private fun createReserveEvent(): Event {
        return ReserveEvent("discount")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createSalesEvent(): Event {
        return SalesEvent("apple")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createScheduleEvent(): Event {
        return ScheduleEvent("schedule")
            .addPredefinedParameter(PredefinedString.ORDER_ID, "23123")
            .addPredefinedParameter(PredefinedFloat.PRICE, 2.19f)
            .addPredefinedParameter(PredefinedLong.QUANTITY, 1L)
    }

    private fun createSearchEvent(): Event {
        return SearchEvent("browser")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createShareEvent(): Event {
        return ShareEvent("telegram")
            .addPredefinedParameter(PredefinedString.RECEIPT_ID, "22")
    }

    private fun createSpendCreditsEvent(): Event {
        return SpendCreditsEvent("boosters")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createStartRegistrationEvent(): Event {
        return StartRegistrationEvent("referrer")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createStartTrialEvent(): Event {
        return StartTrialEvent("30-days")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createStartTutorialEvent(): Event {
        return StartTutorialEvent("video-feature")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createSubmitApplicationEvent(): Event {
        return SubmitApplicationEvent("submit")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createSubscribeEvent(): Event {
        return SubscribeEvent("wire")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createTravelBookingEvent(): Event {
        return TravelBookingEvent("booking").apply {
            addPredefinedParameter(PredefinedLong.NUM_ADULTS, 1L)
            addPredefinedParameter(PredefinedLong.NUM_CHILDREN, 2L)
            addPredefinedParameter(PredefinedLong.NUM_INFANTS, 1L)
            "30.12.2020".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.DATE_A, it)
            }
            "01.01.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.DATE_B, it)
            }
            "01.01.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.DEPARTING_ARRIVAL_DATE, it)
            }
            "30.12.2020".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.DEPARTING_DEPARTURE_DATE, it)
            }
            addPredefinedParameter(PredefinedString.DESTINATION_A, "usa")
            addPredefinedParameter(PredefinedString.DESTINATION_B, "mexico")
            addPredefinedParameter(PredefinedString.DESTINATION_LIST, "usa, mexico")
            addPredefinedParameter(PredefinedLong.HOTEL_SCORE, 5L)
            "01.12.2020".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.TRAVEL_START, it)
            }
            "01.12.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.TRAVEL_END, it)
            }
        }
    }

    private fun createUnlockAchievementEvent(): Event {
        return UnlockAchievementEvent("best damage")
            .addPredefinedParameter(PredefinedLong.USER_SCORE, 12552L)
            .addPredefinedParameter(PredefinedString.ACHIEVEMENT_ID, "1334-1225-ASDZ")
    }

    private fun createUnsubscribeEvent(): Event {
        return UnsubscribeEvent("02.01.2021")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createUpdateEvent(): Event {
        return UpdateEvent("firmware").apply {
            "01.02.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.EVENT_START, it)
            }
            "01.01.2022".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.EVENT_END, it)
            }
            addPredefinedParameter(PredefinedString.NEW_VERSION, "8")
            addPredefinedParameter(PredefinedString.OLD_VERSION, "1.8")
        }
    }

    private fun createViewAdvEvent(): Event {
        return ViewAdvEvent("skip").apply {
            "01.12.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.RETURNING_ARRIVAL_DATE, it)
            }
            "01.12.2021".toTimestamp()?.let {
                addPredefinedParameter(PredefinedLong.RETURNING_DEPARTURE_DATE, it)
            }
        }
    }

    private fun createViewCartEvent(): Event {
        return ViewCartEvent("main")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createViewContentEvent(): Event {
        return ViewContentEvent("ViewContent")
            .addPredefinedParameter(PredefinedString.PARAM_01, "param1")
    }

    private fun createViewItemEvent(): Event {
        return ViewItemEvent("main")
            .addPredefinedParameter(PredefinedLong.MAX_RATING_VALUE, 5L)
            .addPredefinedParameter(PredefinedLong.RATING_VALUE, 9L)
    }

    private fun createViewItemsEvent(): Event {
        return ViewItemsEvent("main")
            .addPredefinedParameter(PredefinedLong.MAX_RATING_VALUE, 5L)
            .addPredefinedParameter(PredefinedLong.RATING_VALUE, 9L)
    }

    private fun createUnsubscriptionEvent() = UnsubscriptionEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Unsubscription"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createConvertedTrialFromRetryEvent() = ConvertedTrialFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createConvertedOfferFromRetryEvent() = ConvertedOfferFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createRenewedSubscriptionFromRetryEvent() = RenewedSubscriptionFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createReactivatedSubscriptionEvent() = ReactivatedSubscriptionEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedTrialEvent() = FailedTrialEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedOfferiseEvent() = FailedOfferiseEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedSubscriptionEvent() = FailedSubscriptionEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedTrialFromRetryEvent() = FailedTrialFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedOfferFromRetryEvent() = FailedOfferFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createFailedSubscriptionFromRetryEvent() = FailedSubscriptionFromRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createRenewedSubscriptionEvent() = RenewedSubscriptionEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createTrialInRetryEvent() = TrialInRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createOfferInRetryEvent() = OfferInRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createSubscriptionInRetryEvent() = SubscriptionInRetryEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createInitialOfferEvent() = InitialOfferEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createInitialTrialEvent() = InitialTrialEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createInitialSubscriptionEvent() = InitialSubscriptionEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createConvertedTrialEvent() = ConvertedTrialEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)

    private fun createConvertedOfferEvent() = ConvertedOfferEvent(
        JSONObject().apply {
            put("affise_event_revenue", 2.99)
            put("affise_event_currency", "USD")
            put("affise_event_product_id", 278459628375)
        },
        "Subscription Plus"
    )
        .addPredefinedParameter(PredefinedFloat.REVENUE, 225522.0f)
}