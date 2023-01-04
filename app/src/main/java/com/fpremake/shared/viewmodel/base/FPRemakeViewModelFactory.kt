package com.fpremake.shared.viewmodel.base

import androidx.collection.ArrayMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpremake.screens_post_login.screen_dashboard.presentation.DashboardScreenViewModel
import com.fpremake.shared.FPRemakeApplication
import com.fpremake.shared.data.realm.UserRealmRepository

import java.util.concurrent.Callable
import javax.inject.Singleton

/**
 * <h1>Food Panda ViewModel factory</h1>
 *
 * @constructor Create an empty Food Panda view model factory
 */
@Singleton
class FPRemakeViewModelFactory() : ViewModelProvider.Factory {

    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    val realmDB = UserRealmRepository.realmInstance

    //User Repository for user related operation.
    private var userRepository: UserRealmRepository = UserRealmRepository

    /**
     *  ViewModels cannot be injected directly because they won't be bound to the owner's view model scope.
     */

    init {
        //ViewModel Initialization..
        creators[DashboardScreenViewModel::class.java] = Callable<ViewModel>
        {
            //2nd Parameter idher set horaha hai(i.e. Callable<out ViewModel>)
            //a.k.a modelClass
            DashboardScreenViewModel(application = FPRemakeApplication.getInstance(), userRepository)
        }
    }

    /**
     * Create
     *
     * @param           T                   Accepts any kind of provided dataType & values
     * @param           modelClass
     * @return                              If calling class is same as defined in object then only it will return {@code true},
     *                                      else it will throw exception and the method will not be execute.
     */

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]

        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }

        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }

        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}