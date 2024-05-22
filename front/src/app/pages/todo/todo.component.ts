import { Component, OnChanges, OnInit, SimpleChanges, ɵUSE_RUNTIME_DEPS_TRACKER_FOR_JIT } from '@angular/core';
import { ITodoStatus, TodoCardComponent } from '../../shared/components/todo-card/todo-card.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SlidePanelComponent } from '../../shared/ui/slide-panel/slide-panel.component';
import { TodoService } from '../../core/services/todo.service';
import { ITodo } from '../../core/models/todo.model';
import { response } from 'express';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [TodoCardComponent, SlidePanelComponent, ReactiveFormsModule],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.scss'
})
export class TodoComponent implements OnInit {
  todoForm!: FormGroup;
  todos: ITodo[] = [];
  todoStatus = ITodoStatus;
  isSlidePanelOpen = false;
  todoId: number | null = null;

  constructor(private todoService: TodoService, private fb: FormBuilder) {
    this.todoForm = this.fb.group({
      title: new FormControl('', [Validators.required]),
      description : new FormControl('', [Validators.required]),
      status: new FormControl('OPEN', [Validators.required]),
    });
  }

  ngOnInit(): void {
      this.getAllTodos();
  }

  getAllTodos() {
    this.todoService.getAllTodo().subscribe({
      next:(response) => {
        this.todos = response
      }
    });
  }

  openSlidePanel() {
    this.isSlidePanelOpen = true;
  }

  onCloseSidePanel() {
    this.isSlidePanelOpen = false;
  }

  onSubmit() {
    if(this.todoForm.valid){
      if(this.todoId) {
        this.todoService.updateTodo(this.todoId, this.todoForm.value).subscribe({
          next: (reponse) => {
            this.getAllTodos();
            this.onCloseSidePanel();
          }
        })
      } else {
        this.todoService.addTodo(this.todoForm.value).subscribe({
          next: (reponse) => {
            this.getAllTodos();
            this.onCloseSidePanel();
            this.todoForm.reset();
          }
        })
      }
    }else {
      this.todoForm.markAllAsTouched();
    }
  }

  onLoadTodoForm(item: ITodo) {
    this.todoId = item.id!!;
    this.todoForm.patchValue({
      title: item.title,
      description: item.description,
      status: item.status
    })
    this.openSlidePanel();
  }

  onDelete(id: number | null) {
    if(id !== null && this.todoId !== null) {
    this.todoService.deleteTodo(id).subscribe({
      next: (reponse) => {
        this.getAllTodos();
        this.onCloseSidePanel();
      }
    })
    }
  }

}
