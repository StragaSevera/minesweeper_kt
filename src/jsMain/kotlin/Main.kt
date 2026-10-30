import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.main
import react.dom.html.ReactHTML.p
import react.useState
import web.dom.document
import web.dom.ElementId

val App = FC<Props> {
    var name by useState("")

    main {
        h1 { +"Hello, React!" }
        label {
            htmlFor = ElementId("name")
            +"Your name"
        }
        input {
            id = ElementId("name")
            placeholder = "Type your name"
            value = name
            onChange = { name = it.currentTarget.value }
        }
        p {
            id = ElementId("greeting")
            +if (name.isBlank()) "Start typing to see your greeting." else "Hello, ${name.trim()}!"
        }
        p { +"Characters: ${name.length}" }
        button {
            disabled = name.isEmpty()
            onClick = { name = "" }
            +"Reset"
        }
    }
}

fun main() {
    val container = requireNotNull(document.getElementById(ElementId("root")))
    createRoot(container).render(App.create())
}
