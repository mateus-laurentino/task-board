import { loadCreateModal, openModal } from './create-card-modal.js';
import { buildCard, loadManagerModal, showError } from './card-modal.js';

document.addEventListener("DOMContentLoaded", async () => {

  const boardContainer = document.getElementById("boardContainer");

  await loadCreateModal();
  await loadManagerModal();

  const response = await fetch('http://localhost:8080/board');
  const boards = await response.json();

  if(!boards.success)
    return showError(boards.errors);

  boards.result.forEach(column => {
    const columnDiv = document.createElement("div");
    columnDiv.className = "column";
    columnDiv.dataset.position = column.position;

    const header = createColumnHeader(column);
    columnDiv.appendChild(header);

    const cardList = createCardList(column, columnDiv);

    columnDiv.appendChild(cardList);
    boardContainer.appendChild(columnDiv);
  });
});

function createColumnHeader(column){
    const columnName = document.createElement("h4");
    columnName.innerHTML = column.name;
    columnName.style.color = column.fontColor;

    const header = document.createElement("div");
    header.className = "column-header";
    header.style.background = column.backgroundColor
    header.appendChild(columnName);

    return header;
}

function createCardList(column, columnDiv){
  const cardList = document.createElement("div");
  cardList.className = "card-list";
  
  addDragInDrop(cardList, column);

  if (column.position === 1)
  {
    const btnAddCard = createAddButton(cardList, column)
    columnDiv.appendChild(btnAddCard);
  }

  column.cardDetails.forEach(cardData => {
    buildCard(cardList, cardData, column)
  });

  return cardList;
}

function createAddButton(cardList, column){
      const addBtn = document.createElement("button");
      addBtn.className = "add-card-btn";
      addBtn.innerText = "+";
      addBtn.type = "button";

      addBtn.addEventListener("click", async () => {
        openModal(cardList, column)
      });

      return addBtn;
}

function addDragInDrop(cardList, column){
    cardList.addEventListener("dragover", (e) => {
      e.preventDefault();
      cardList.classList.add("dragover");
    });

    cardList.addEventListener("dragleave", () => {
      cardList.classList.remove("dragover");
    });

    cardList.addEventListener("drop", async e => await dropCard(e, cardList, column))
}

async function dropCard(e, cardList, column){
  e.preventDefault();

  cardList.classList.remove("dragover");

  const dragging = document.querySelector(".dragging");
  if (!dragging || !dragging.classList.contains("div-card")) return;

  const sourceCardId = e.dataTransfer.getData("sourceCardId");
  if (!sourceCardId)
    return;
  
  const cardUpdateResponse = await fetch(`http://localhost:8080/card/${sourceCardId}/board/${column.id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: null,
  });

  const cardData = await cardUpdateResponse.json();

  dragging.remove();
  
  buildCard(cardList, cardData.result, column);
}