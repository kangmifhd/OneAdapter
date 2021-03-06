@file:Suppress("ClassName")

package com.idanatz.oneadapter.tests.module_configs.layout_module_config

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.idanatz.oneadapter.external.modules.ItemModule
import com.idanatz.oneadapter.helpers.BaseTest
import com.idanatz.oneadapter.external.MissingConfigArgumentException
import com.idanatz.oneadapter.models.TestModel
import org.junit.Test
import org.junit.runner.RunWith

private const val INVALID_RESOURCE = -1

@RunWith(AndroidJUnit4::class)
class WhenUsingInvalidLayoutResource_ThenAttachItemModule_ShouldThrowException : BaseTest() {

    @Test(expected = MissingConfigArgumentException::class)
    fun test() {
        configure {
            act {
                oneAdapter.attachItemModule(TestItemModule())
            }
        }
    }

    class TestItemModule : ItemModule<TestModel>() {
        init {
            config {
                layoutResource = INVALID_RESOURCE
            }
        }
    }
}