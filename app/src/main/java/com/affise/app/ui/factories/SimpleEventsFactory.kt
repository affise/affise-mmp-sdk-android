package com.affise.app.ui.factories

import com.affise.attribution.events.Event
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

class SimpleEventsFactory : EventsFactory {
    override fun createEvents(): List<Event> {
        return listOf(
            AchieveLevelEvent(),
            AddPaymentInfoEvent(),
            AddToCartEvent(),
            AddToWishlistEvent(),
            AdRevenueEvent(),
            ClickAdvEvent(),
            CompleteRegistrationEvent(),
            CompleteStreamEvent(),
            CompleteTrialEvent(),
            CompleteTutorialEvent(),
            ContactEvent(),
            ContentItemsViewEvent(),
            CustomId01Event(),
            CustomId02Event(),
            CustomId03Event(),
            CustomId04Event(),
            CustomId05Event(),
            CustomId06Event(),
            CustomId07Event(),
            CustomId08Event(),
            CustomId09Event(),
            CustomId10Event(),
            CustomizeProductEvent(),
            DeepLinkedEvent(),
            DonateEvent(),
            FindLocationEvent(),
            InitiateCheckoutEvent(),
            InitiatePurchaseEvent(),
            InitiateStreamEvent(),
            InviteEvent(),
            LastAttributedTouchEvent(),
            LeadEvent(),
            ListViewEvent(),
            LoginEvent(),
            OpenedFromPushNotificationEvent(),
            OrderEvent(),
            OrderItemAddedEvent(),
            OrderItemRemoveEvent(),
            OrderCancelEvent(),
            OrderReturnRequestEvent(),
            OrderReturnRequestCancelEvent(),
            PurchaseEvent(),
            RateEvent(),
            ReEngageEvent(),
            ReserveEvent(),
            SalesEvent(),
            ScheduleEvent(),
            SearchEvent(),
            ShareEvent(),
            SpendCreditsEvent(),
            StartRegistrationEvent(),
            StartTrialEvent(),
            StartTutorialEvent(),
            SubmitApplicationEvent(),
            SubscribeEvent(),
            TravelBookingEvent(),
            UnlockAchievementEvent(),
            UnsubscribeEvent(),
            UpdateEvent(),
            ViewAdvEvent(),
            ViewCartEvent(),
            ViewContentEvent(),
            ViewItemEvent(),
            ViewItemsEvent(),
            UnsubscriptionEvent(),
            ConvertedTrialFromRetryEvent(),
            ConvertedOfferFromRetryEvent(),
            RenewedSubscriptionFromRetryEvent(),
            ReactivatedSubscriptionEvent(),
            FailedTrialEvent(JSONObject()),
            FailedOfferiseEvent(JSONObject()),
            FailedSubscriptionEvent(),
            FailedTrialFromRetryEvent(),
            FailedOfferFromRetryEvent(),
            FailedSubscriptionFromRetryEvent(),
            RenewedSubscriptionEvent(),
            TrialInRetryEvent(),
            OfferInRetryEvent(),
            SubscriptionInRetryEvent(),
            InitialOfferEvent(),
            InitialTrialEvent(),
            InitialSubscriptionEvent(),
            ConvertedTrialEvent(),
            ConvertedOfferEvent(),
        )
    }
}
