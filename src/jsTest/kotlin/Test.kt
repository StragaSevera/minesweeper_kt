import react.create
import react.dom.client.createRoot
import react.dom.flushSync
import web.dom.document
import web.html.HTMLButtonElement
import web.html.HTMLInputElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BrowserTest {
    @Test
    fun inputUpdatesGreetingAndResetClearsIt() {
        val container = document.createElement("div")
        document.body.appendChild(container)
        val root = createRoot(container)
        try {
            flushSync { root.render(App.create()) }
            val input = container.querySelector("input") as HTMLInputElement
            val reset = container.querySelector("button") as HTMLButtonElement
            assertTrue(reset.disabled)

            flushSync {
                // Use the native setter so React observes an actual input change.
                js("Object.getOwnPropertyDescriptor(HTMLInputElement.prototype, 'value').set.call(input, 'Ada')")
                input.dispatchEvent(js("new Event('input', { bubbles: true })"))
            }
            assertEquals("Hello, Ada!", container.querySelector("#greeting")?.textContent)
            assertTrue(container.textContent.orEmpty().contains("Characters: 3"))

            flushSync { reset.click() }
            assertEquals("", input.value)
            assertEquals("Start typing to see your greeting.", container.querySelector("#greeting")?.textContent)
            assertTrue(reset.disabled)
        } finally {
            flushSync { root.unmount() }
            container.remove()
        }
    }
}
