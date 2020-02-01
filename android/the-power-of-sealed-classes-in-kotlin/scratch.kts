/// Copy and paste the contents of this file into a scratch file in Android Studio or IntelliJ to try it out.


import java.util.UUID

// AuthenticationState

sealed class AuthenticationState {
    data class SignedIn(val userGuid: UUID) : AuthenticationState()
    data class StoredCredentials(val credentials: Credentials) : AuthenticationState()
    object SignedOut : AuthenticationState()
}

data class Credentials(
    val username: String,
    val token: String
)

fun onAuthenticationStateChanged(newState: AuthenticationState) = when (newState) {
    is AuthenticationState.SignedIn -> showSignedIn(newState.userGuid)
    is AuthenticationState.StoredCredentials -> showSignedIn(newState.credentials)
    AuthenticationState.SignedOut -> showSignedOut()
}

fun showSignedIn(userGuid: UUID) {}
fun showSignedIn(storedCredentials: Credentials) {}
fun showSignedOut() {}


// ViewState

sealed class ViewState {
    object LoadingState : ViewState()
    data class PresentingState(val viewData: ContactsViewData) : ViewState()
    data class ErrorState(val message: String) : ViewState()
}

data class ContactsViewData(
    val nameLabel: String,
    val unreadCountLabel: String
)

fun renderViewState(viewState: ViewState) = when (viewState) {
    ViewState.LoadingState -> showLoadingViews()
    is ViewState.PresentingState -> showPresentingViews(viewState.viewData)
    is ViewState.ErrorState -> showErrorViews(viewState.message)
}

fun showLoadingViews() {}
fun showPresentingViews(viewData: ContactsViewData) {}
fun showErrorViews(message: String) {}


// AnalyticsEvent

sealed class AnalyticsEvent {
    object AccountCreated : AnalyticsEvent()
    data class MessageSent(val conversation: Conversation) : AnalyticsEvent()
    data class ProfileOpened(val participant: Participant) : AnalyticsEvent()

    fun parameters(): Map<Parameter, String> = when (this) {
        AccountCreated -> mapOf()
        is MessageSent -> mapOf(
            Parameter.PARTICIPANT_COUNT to conversation.participants.size.toString(),
            Parameter.IS_ARCHIVED to conversation.isArchived.toString()
        )
        is ProfileOpened -> mapOf(
            Parameter.HAS_ACCOUNT to participant.hasCreatedAccount.toString()
        )
    }

    enum class Parameter {
        HAS_ACCOUNT,
        IS_ARCHIVED,
        PARTICIPANT_COUNT,
    }
}

data class Conversation(val participants: List<Participant>, val isArchived: Boolean)
data class Participant(val hasCreatedAccount: Boolean)
