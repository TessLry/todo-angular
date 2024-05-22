import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiEndpoint } from '../constants/constants';
import { ITodo } from '../models/todo.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) { }

  getAllTodo(): Observable<ITodo[]> {
    return this.http.get<ITodo[]>(`${apiEndpoint.TodoEndpoint.getAllTodo}`);
  }

  addTodo(data: ITodo): Observable<ITodo> {
    return this.http.post<ITodo>(`${apiEndpoint.TodoEndpoint.addTodo}`, data)
  }

  updateTodo(id: number, data: ITodo): Observable<ITodo> {
    return this.http.put<ITodo>(`${apiEndpoint.TodoEndpoint.updateTodo}/${id}`, data)
  }

  deleteTodo(id: number): Observable<ITodo> {
    return this.http.delete<ITodo>(`${apiEndpoint.TodoEndpoint.updateTodo}/${id}`)
  }
}
 