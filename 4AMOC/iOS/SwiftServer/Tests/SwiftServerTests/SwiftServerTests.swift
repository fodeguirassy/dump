import XCTest
@testable import SwiftServer

class SwiftServerTests: XCTestCase {
    func testExample() {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct
        // results.
        XCTAssertEqual(SwiftServer().text, "Hello, World!")
    }


    static var allTests = [
        ("testExample", testExample),
    ]
}
