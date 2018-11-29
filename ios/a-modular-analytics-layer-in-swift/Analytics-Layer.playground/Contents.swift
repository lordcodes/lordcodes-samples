// MARK: Event

enum AnalyticsEvent: Equatable {
    case addContactTapped
    case contactSelected(index: Int)
    case messageSent(threadType: ThreadType, length: Int)
    case userSignedIn

    var parameters: [Parameter: String] {
        switch self {
        case .addContactTapped,
             .userSignedIn:
            return [:]
        case let .contactSelected(index):
            return [
                .contactIndex: String(describing: index)
            ]
        case let .messageSent(threadType, length):
            return [
                .threadType: threadType.rawValue,
                .messageLength: String(describing: length)
            ]
        }
    }

    enum Parameter {
        case contactIndex
        case messageLength
        case threadType
    }
}

enum ThreadType: String, Equatable {
    case chat
    case email
    case sms
}

// MARK: Provider

protocol AnalyticsProvider {
    func report(event: AnalyticsEvent)
}

class FirebaseAnalyticsProvider: AnalyticsProvider {
    private let eventMapper = AnalyticsEventMapper()

    func report(event: AnalyticsEvent) {
        let name = eventMapper.eventName(for: event)
        let parameters = eventMapper.parameters(for: event)
        Analytics.logEvent(name, parameters: parameters)
    }
}

class Analytics {
    static func logEvent(_ name: String, parameters: [String: String]) {
    }
}

class LoggingAnalyticsProvider: AnalyticsProvider {
    private let eventMapper = AnalyticsEventMapper()

    func report(event: AnalyticsEvent) {
        let name = eventMapper.eventName(for: event)
        print("Event reported: \(name)")
    }
}

class TestingAnalyticsProvider: AnalyticsProvider {
    var eventsReported = [AnalyticsEvent]()

    func report(event: AnalyticsEvent) {
        eventsReported.append(event)
    }
}

// MARK: Mapper

class AnalyticsEventMapper {
    func eventName(for event: AnalyticsEvent) -> String {
        switch event {
        case .addContactTapped:
            return "addContact_tapped"
        case .contactSelected:
            return "contact_selected"
        case .messageSent:
            return "message_sent"
        case .userSignedIn:
            return "user_signedIn"
        }
    }

    func parameters(for event: AnalyticsEvent) -> [String: String] {
        return event.parameters.mapKeys { parameterName(for: $0) }
    }

    func parameterName(for parameter: AnalyticsEvent.Parameter) -> String {
        switch parameter {
        case .contactIndex:
            return "contact_index"
        case .messageLength:
            return "message_length"
        case .threadType:
            return "thread_type"
        }
    }
}

extension Dictionary {
    func mapKeys<NewKeyT>(_ transform: (Key) throws -> NewKeyT) rethrows -> [NewKeyT: Value] {
        var newDictionary = [NewKeyT: Value]()
        try forEach { key, value in
            let newKey = try transform(key)
            newDictionary[newKey] = value
        }
        return newDictionary
    }
}

// MARK: Reporter

class AnalyticsReporter {
    private let providers: [AnalyticsProvider]

    init(providers: [AnalyticsProvider]) {
        self.providers = providers
    }

    func report(event: AnalyticsEvent) {
        providers.forEach {
            $0.report(event: event)
        }
    }
}

// MARK: Use

extension AnalyticsReporter {
    enum Test {
        func instance() -> AnalyticsReporter {
            return AnalyticsReporter(providers: [TestingAnalyticsProvider()])
        }
    }
}

class ContactsViewModel {
    private let coordinator: ContactsCoordinator
    private let analyticsReporter: AnalyticsReporter

    init(coordinator: ContactsCoordinator, analyticsReporter: AnalyticsReporter) {
        self.coordinator = coordinator
        self.analyticsReporter = analyticsReporter
    }

    func contactSelected(at index: Int) {
        analyticsReporter.report(event: .contactSelected(index: index))
        coordinator.startContactThreads(at: index)
    }

    func addContactTapped() {
        analyticsReporter.report(event: .addContactTapped)
        coordinator.startContactCreation()
    }
}

class ContactsCoordinator {
    func startContactThreads(at index: Int) {
        // Do something
    }

    func startContactCreation() {
        // Do something
    }
}

let reporter = AnalyticsReporter(providers: [
    LoggingAnalyticsProvider(),
    FirebaseAnalyticsProvider()
])
let viewModel = ContactsViewModel(coordinator: ContactsCoordinator(), analyticsReporter: reporter)

viewModel.contactSelected(at: 3)
viewModel.addContactTapped()
