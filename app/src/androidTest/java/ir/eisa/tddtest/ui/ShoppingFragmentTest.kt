package ir.eisa.tddtest.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.eisa.tddtest.R
import ir.eisa.tddtest.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShoppingFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun clickAppShoppingItemButton_navigateToAddShoppingItemFragment(){

        val navController= mock(NavController::class.java)

        launchFragmentInHiltContainer<ShoppingFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }

        onView(withId(R.id.fabAddShoppingItem)).perform(click())

        verify(navController.navigate(
            ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemShoppingFragment()
        ))
    }
}