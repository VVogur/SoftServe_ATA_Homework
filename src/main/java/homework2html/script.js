let isSortedAsc = false;
const sortTable  = e => {
  e.preventDefault();
  if (isSortedAsc) {
     sort();
  } else {
     revsort();
  }

  isSortedAsc = !isSortedAsc;
}

function sort() {
  let sortedRows = Array.from(table.rows)
  .slice(1)
  .sort((rowA, rowB) => rowA.cells[0].innerHTML > rowB.cells[0].innerHTML ? 1 : -1);

table.tBodies[0].append(...sortedRows);
}

function revsort() {
  let reverseRows = Array.from(table.rows)
  .slice(1)
  .sort((rowB, rowA) => rowA.cells[0].innerHTML > rowB.cells[0].innerHTML ? 1 : -1);

table.tBodies[0].append(...reverseRows);
}

document.querySelector('.file_set').addEventListener('click', sortTable);