import tasks from './data.js';

const element = document.createElement('p');
element.innerHTML = 'Hello world! This is a test of the vanilla JS template.';

const root = document.getElementById('root');
root.appendChild(element);

tasks.forEach((task) => {
  const taskElement = document.createElement('div');
  taskElement.className = 'task';
  taskElement.innerHTML = `
        <h2>${task.name}</h2>
        <p>${task.description}</p>
        <p>Status: ${task.state}</p>
    `;
  root.appendChild(taskElement);
});
