import obj, {tasks} from './data.js';



console.log(obj);
const taskColumnElement = (list) => {
  const taskColumn = document.createElement('div');
  taskColumn.className = 'task-column';
  list.forEach((task) => {
    const taskElement = document.createElement('div');
    taskElement.className = 'task';
    taskElement.innerHTML = `
              <h2>${task.name}</h2>
              <p>${task.description}</p>
              <p>Status: ${task.state}</p>
          `;
    taskElement.setAttribute('draggable', 'true');
    taskElement.setAttribute('data-id', task.id);
    taskElement.setAttribute('data-state', task.state);
    taskElement.addEventListener('dragstart', (e) => {
      e.dataTransfer.setData('text/plain', task.id);
      e.dataTransfer.effectAllowed = 'move';
    });
    taskColumn.appendChild(taskElement);
  });
  return taskColumn;
};

const element = document.createElement('p');
element.innerHTML = 'Hello world! This is a test of the vanilla JS template.';

const root = document.getElementById('root');

root.appendChild(element);

const taskList = document.createElement('div');
taskList.className = 'container';

root.appendChild(taskList);

const groupedTasks = tasks.reduce((acc, task) => {
  if (!acc[task.state]) {
    acc[task.state] = [];
  }
  acc[task.state].push(task);
  return acc;
}, {});

console.log(groupedTasks);

for (const k in groupedTasks) {
  const taskGroup = document.createElement('div');
  taskGroup.className = 'task-group';
  taskGroup.innerHTML = `<h1>${k}</h1>`;
  const taskColumn = taskColumnElement(groupedTasks[k]);
  taskGroup.appendChild(taskColumn);
  
  
  taskGroup.setAttribute('ondragover', 'event.preventDefault()');
  taskGroup.addEventListener('drop', (e) => {
    e.preventDefault();
    console.log('Dropped on group:', e);
    const taskId = e.dataTransfer.getData('text/plain');
    const taskElement = document.querySelector(`[data-id="${taskId}"]`);
    const newState = k;
    taskElement.setAttribute('data-state', newState);
    taskElement.querySelector(
      'p:nth-child(3)'
    ).innerText = `Status: ${newState}`;
    const taskColumnElement = taskGroup.querySelector('.task-column');
    taskColumnElement.appendChild(taskElement);
  });

  taskList.appendChild(taskGroup);
}

const button = document.createElement('button');
button.innerHTML = 'Print tasks';
button.addEventListener('click', () => {
  console.log(tasks);
});
root.appendChild(button);
