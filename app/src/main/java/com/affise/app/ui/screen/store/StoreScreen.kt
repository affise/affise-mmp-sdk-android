package com.affise.app.ui.screen.store

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.affise.app.R
import com.affise.app.ui.popDialog
import com.affise.app.ui.theme.AffiseAttributionLibTheme
import com.affise.attribution.Affise
import com.affise.attribution.modules.subscription.AffiseProduct
import com.affise.attribution.modules.subscription.AffiseProductsResult
import com.affise.attribution.modules.subscription.AffisePurchasedInfo
import com.affise.attribution.modules.subscription.AffiseResult

@Preview(
    showBackground = true,
    name = "Store Screen Preview",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun StoreScreenPreview() {
    hasSubscriptionModule.value = true
    AffiseAttributionLibTheme {
        StoreScreen()
    }
}

private val ids = listOf(
    "com.test.invalid",
    "com.test.sub_1",
    "com.test.sub_2",
    "com.test.sub_3",
    "com.test.prod_1",
    "com.test.prod_2",
    "com.test.prod_3",
)

var fetchProductsResult = mutableStateOf<AffiseProductsResult?>(null)

fun fetchProducts(onError: ((String?, List<String>?) -> Unit)? = null) {
    Affise.Module.Subscription.fetchProducts(ids) { result ->
        when (result) {
            is AffiseResult.Success -> {
                fetchProductsResult.value = result.value
                if (result.value.invalidIds.isNotEmpty()) {
                    onError?.invoke(null, result.value.invalidIds)
                }
            }

            is AffiseResult.Error -> {
                fetchProductsResult.value = null
                onError?.invoke(result.error.message, null)
            }
        }
    }
}

fun purchase(
    activity: Activity?,
    product: AffiseProduct,
    onResult: (AffisePurchasedInfo?, String?) -> Unit,
) {
    activity?.let {
        Affise.Module.Subscription.purchase(it, product, null) { result ->
            when (result) {
                is AffiseResult.Success -> onResult(result.value, null)
                is AffiseResult.Error -> onResult(null, result.error.message)
            }
        }
    } ?: onResult(null, "no activity")
}

private val hasSubscriptionModule = mutableStateOf(Affise.Module.Subscription.hasModule())

@Composable
fun StoreScreen() {
    if (hasSubscriptionModule.value) {
        Products()
    } else {
        Text(text = stringResource(R.string.no_subscription_module))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Products() {
    val activity = LocalContext.current.findActivity()

    val fetchResultTitle = stringResource(R.string.fetch_result)
    val fetchResultErrorTitle = stringResource(R.string.fetch_result_error)
    val invalidIdsTitle = stringResource(R.string.invalid_ids)

    LaunchedEffect(Unit) {
        fetchProducts { error, invalidIds ->
            if (!error.isNullOrEmpty()) {
                alertDialog(fetchResultErrorTitle, "$error")
            }
            if (!invalidIds.isNullOrEmpty()) {
                println("Affise: fetchProducts invalidIds = [${invalidIds.joinToString(", ")}]")
//                alertDialog(fetchResultTitle, "$invalidIdsTitle: $ids")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                ) {
                    stickyHeader {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.9f))
                        ) {
                            Text(
                                text = stringResource(R.string.products),
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                    }
                    fetchProductsResult.value?.let {
                        val products = it.products
                        itemsIndexed(items = products) { idx, product ->
                            ProductItem(
                                product,
                                activity,
                                products.size == idx + 1
                            )
                        }
                    }
                }
            }
        }


        Button(
            onClick = {
                fetchProducts { error, ids ->
                    if (!error.isNullOrEmpty()) {
                        alertDialog(fetchResultErrorTitle, "$error")
                    }
                    if (!ids.isNullOrEmpty()) {
                        alertDialog(fetchResultTitle, "$invalidIdsTitle: $ids")
                    }
                }
            }, shape = RoundedCornerShape(10.dp)
        ) {
            Text(stringResource(R.string.fetch_products))
        }
    }
}

@Composable
fun ProductItem(
    product: AffiseProduct,
    activity: Activity?,
    last: Boolean = false,
) {
    val purchaseResultTitle = stringResource(R.string.purchase_result)
    val purchaseResultErrorTitle = stringResource(R.string.purchase_result_error)

    Row(
        modifier = Modifier
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(product.title, color = MaterialTheme.colorScheme.onPrimary)
            Text(
                product.productDescription,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
            product.subscription?.let {
                Text("id: ${it.offerId}", color = MaterialTheme.colorScheme.onBackground.copy(0.6f))
                Text("${it.numberOfUnits} ${it.timeUnit?.value}", color = Color.Blue.copy(0.6f))
            }
        }
        Column {
            Text(
                "${"%.2f".format(product.price?.value ?: "-")} ${product.price?.currencyCode ?: "-"}",
                modifier = Modifier.padding(horizontal = 4.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        TextButton(
            onClick = {
                purchase(activity, product) { data, error ->
                    if (!error.isNullOrEmpty()) {
                        alertDialog(purchaseResultErrorTitle, "$error")
                    } else {
                        alertDialog(purchaseResultTitle, "$data")
                    }
                }
            }
        ) {
            Text(stringResource(R.string.buy), color = Color.Blue)
        }
    }
    if (!last) {
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary.copy(0.1f)
        )
    }
}

private fun alertDialog(
    title: String,
    text: String,
) {
    popDialog(title = title, text = text)
}

tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}