function fetchData(url, options = {}) {
    return fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            throw error;
        });
}



let form = document.getElementById("mushroomForm")

form.addEventListener("submit", (e)=> {
    e.preventDefault();

    const formData = new FormData(form)
    let object = {}

    formData.forEach(function (value, key) {
        console.log(key  + value)
        object[key] = value;
    })
    let json = JSON.stringify(object)
    console.log(json)
    fetch('http://localhost:8080/TestDataBase_war_exploded/restapi/mushrooms/insert', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: json,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            response.json()
                .then(data => {
                    console.log('Server Response:', data);
                })
                .catch(error => {
                    console.error('Error submitting form:', error);
                });
        })
})
let buttonElement = document.getElementById("button1");

buttonElement.addEventListener("click", () => {
    fetchData('http://localhost:8080/TestDataBase_war_exploded/restapi/mushrooms/all', {
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(data => {
            for (let mushroom of data) {
                console.log(mushroom)
                createParagraph(mushroom["latinName"],mushroom["englishName"],mushroom["color"])
            }

        })
        .catch(error => {
            // Handle errors if needed
            console.error('Error in click event handler:', error);
        });
});
function createParagraph(name,englishName,color) {

    let paragraph = document.createElement("p");

    let textNode = document.createTextNode(name + " " + englishName + " " + color);
    paragraph.appendChild(textNode);

    paragraph.setAttribute("id", name);
    paragraph.style.color = color;

    document.body.appendChild(paragraph);


}

