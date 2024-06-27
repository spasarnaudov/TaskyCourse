package com.spascoding.taskycourse.feature_auth.presentation.register

import com.google.common.truth.Truth.assertThat
import com.spascoding.taskycourse.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.spascoding.taskycourse.feature_auth.data.repository.FakeAuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var authRepository: FakeAuthRepository

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        authRepository = FakeAuthRepository()
        viewModel = RegisterViewModel(authRepository)
    }

    @Test
    fun testChangeName() = runTest {
        val name = "Test User"
        val event = RegisterEvent.ChangeName(name)
        viewModel.onEvent(event)

        val actual = viewModel.state.value
        val expected = RegisterViewModelState(name = name, validName = true)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testChangeEmail() = runTest {
        val email = "test@example.com"
        val event = RegisterEvent.ChangeEmail(email)
        viewModel.onEvent(event)

        val actual = viewModel.state.value
        val expected = RegisterViewModelState(email = email, validEmail = true)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testChangePassword() = runTest {
        val password = "Password123"
        val event = RegisterEvent.ChangePassword(password)
        viewModel.onEvent(event)

        val actual = viewModel.state.value
        val expected = RegisterViewModelState(password = password, validPassword = true)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testRegisterActionSuccess() = runTest {
        val name = "Test User"
        val email = "test@example.com"
        val password = "Password123"
        val event = RegisterEvent.RegisterAction(name, email, password)

        // Stubbing the repository methods directly
        authRepository.stubRegisterSuccess()

        viewModel.onEvent(event)
        advanceUntilIdle()

        val actual = viewModel.toastMessages.first()
        assertThat(actual is RegisterViewModel.UserEvent.Success).isTrue()
    }

    @Test
    fun testRegisterActionFailure() = runTest {
        val name = "Test User"
        val email = "test@example.com"
        val password = "password123"
        val event = RegisterEvent.RegisterAction(name, email, password)

        // Stubbing the repository methods directly
        authRepository.stubRegisterFailure()

        viewModel.onEvent(event)
        advanceUntilIdle()

        val actual = viewModel.toastMessages.first()
        assertThat(actual is RegisterViewModel.UserEvent.Error).isTrue()
    }
}