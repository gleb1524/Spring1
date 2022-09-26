import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Page} from "../model/page";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-service',
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.css']
})
export class ProductServiceComponent implements OnInit {

  constructor(private http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Page>('api/v1/product');
  }

  public findById(id: number) {
    return this.http.get<Product>(`api/v1/product/${id}`);
  }

  public save(product: Product) {
    return this.http.put<Product>('api/v1/product', product);
  }

  public delete(id: number){
    return this.http.delete(`api/v1/product/`,{
      params: new HttpParams().set('id', id)
    });
  }



  ngOnInit(): void {
  }

}
