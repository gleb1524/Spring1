import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HeaderComponent} from "./header/header.component";
import {RouterModule} from "@angular/router";
import {ProductListComponent} from "./product-list/product-list.component";
import { FooterComponent } from './footer/footer.component';
import {ProductFormComponent} from "./product-form/product-form.component";
import { AppRoutingModule } from './app-routing.module';
import {ProductServiceComponent} from "./product-service/product-service.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ProductFormComponent,
    ProductListComponent,
    ProductServiceComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    //RouterModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ProductServiceComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
