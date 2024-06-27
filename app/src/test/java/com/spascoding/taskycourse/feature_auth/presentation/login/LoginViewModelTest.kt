package com.spascoding.taskycourse.feature_auth.presentation.login

import com.google.common.truth.Truth.assertThat
import com.spascoding.taskycourse.MainCoroutineRule
import com.spascoding.taskycourse.feature_auth.data.repository.FakeAuthRepository
import com.spascoding.taskycourse.feature_auth.presentation.register.RegisterViewModelState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var authRepository: FakeAuthRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
        viewModel = LoginViewModel(authRepository)
    }

    @Test
    fun testChangeEmail() = runTest {
        val email = "test@example.com"
        val event = LoginEvent.ChangeEmail(email)
        viewModel.onEvent(event)

        val actual = viewModel.state.value
        val expected = RegisterViewModelState(email = email, validEmail = true)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testChangePassword() = runTest {
        val password = "Password123"
        val event = LoginEvent.ChangePassword(password)
        viewModel.onEvent(event)

        val actual = viewModel.state.value
        val expected = RegisterViewModelState(password = password, validPassword = true)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testRegisterActionSuccess() = runTest {
        val email = "test@example.com"
        val password = "Password123"
        val event = LoginEvent.LoginAction(email, password)

        // Stubbing the repository methods directly
        authRepository.stubRegisterSuccess()

        viewModel.onEvent(event)
        advanceUntilIdle()

        val actual = viewModel.toastMessages.first()
        assertThat(actual is LoginViewModel.UserEvent.Success).isTrue()
    }

    @Test
    fun testRegisterActionFailure() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val event = LoginEvent.LoginAction(email, password)

        // Stubbing the repository methods directly
        authRepository.stubRegisterFailure()

        viewModel.onEvent(event)
        advanceUntilIdle()

        val actual = viewModel.toastMessages.first()
        assertThat(actual is LoginViewModel.UserEvent.Error).isTrue()
    }
}