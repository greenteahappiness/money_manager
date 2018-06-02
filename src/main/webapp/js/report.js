function confirmDeleting() {
    return confirm('Do you want to delete this expense?');
}

var deleteButton = document.getElementById("delete-button");
deleteButton.addEventListener('click', confirmDeleting, false);
