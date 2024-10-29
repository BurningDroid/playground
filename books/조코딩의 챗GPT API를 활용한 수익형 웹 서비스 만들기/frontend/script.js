let userMessages = [];
let assistantMessages = [];
let myDateTime = '';

function start() {
    const date = document.getElementById('date').value;
    const hour = document.getElementById('hour').value;
    if (date === '') {
        alert('생년월일을 입력해 주세요');
        return;
    }

    myDateTime = date + hour;
    console.log(myDateTime);

    document.getElementById('intro').style.display = 'none';
    document.getElementById('chat').style.display = 'block';
}

async function sendMessage() {
    document.getElementById('loader').style.display = 'block';

    const messageInput = document.getElementById("messageInput");
    const message = messageInput.value;

    const userBubble = document.createElement("div");
    userBubble.className = "chat-bubble user-bubble";
    userBubble.textContent = message;
    document.getElementById('fortuneResponse').appendChild(userBubble);

    userMessages.push(messageInput.value);

    messageInput.value = "";

    try {
        const response = await fetch("http://localhost:3000/fortuneTell", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                myDateTime: myDateTime,
                userMessages: userMessages,
                assistantMessages: assistantMessages
            })
        });

        if (!response.ok) {
            throw new Error('Request failed with status: ' + response.status);
        }

        const data = await response.json();
        console.log("response: ", data.assistant);
        document.getElementById('loader').style.display = 'none';

        const botBubble = document.createElement("div");
        botBubble.className = "chat-bubble bot-bubble";
        botBubble.textContent = data.assistant;
        document.getElementById("fortuneResponse").appendChild(botBubble);

        assistantMessages.push(data.assistant);
    } catch (error) {
        console.error('error: ', error);
    }
}
