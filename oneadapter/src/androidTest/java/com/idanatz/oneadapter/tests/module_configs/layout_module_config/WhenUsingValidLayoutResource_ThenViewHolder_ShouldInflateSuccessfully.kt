@file:Suppress("ClassName")

package com.idanatz.oneadapter.tests.module_configs.layout_module_config

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.idanatz.oneadapter.external.modules.ItemModule
import com.idanatz.oneadapter.helpers.BaseTest
import com.idanatz.oneadapter.internal.utils.extensions.inflateLayout
import com.idanatz.oneadapter.models.TestModel
import com.idanatz.oneadapter.test.R
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WhenUsingValidLayoutResource_ThenViewHolder_ShouldInflateSuccessfully : BaseTest() {

    private val testedLayoutResource = R.layout.test_model_small
    private var rootView: View? = null

    @Test
    fun test() {
        configure {
            var expectedLayoutId = 0

            prepareOnActivity {
                oneAdapter.attachItemModule(TestItemModule())
                expectedLayoutId = it.inflateLayout(testedLayoutResource).id
            }
            actOnActivity {
                oneAdapter.add(modelGenerator.generateModel())
            }
            untilAsserted {
                expectedLayoutId shouldEqual rootView?.id
            }
        }
    }

    inner class TestItemModule : ItemModule<TestModel>() {
        init {
            config {
                layoutResource = testedLayoutResource
            }
            onBind { _, viewBinder, _ ->
                rootView = viewBinder.rootView
            }
        }
    }
}