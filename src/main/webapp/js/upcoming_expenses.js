var backlog = document.getElementById("backlog");
var addCard = document.getElementById("add-card");
var newCard;

function addNewCard() {
    newCard = document.createElement("div");
    newCard.className = "card portlet";
    cardHeader = document.createElement("div");
    cardHeader.className = "card-header portlet-header";
    header = document.createTextNode("Add card description");
    cardHeader.appendChild(header);
    newCard.appendChild(cardHeader);
    backlog.appendChild(newCard);
    console.log('Created new card!')
}

addCard.addEventListener('click', addNewCard, false);
