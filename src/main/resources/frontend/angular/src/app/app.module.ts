import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSortModule } from '@angular/material/sort';

import { AppComponent } from './app.component';
import { ApiService } from './app.service';
import { environment } from '../environments/environment';
import { API_URL } from './app.tokens';

@NgModule({
	declarations: [
		AppComponent
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		HttpClientModule,
		FormsModule,
		MatSelectModule,
		MatTableModule,
		MatButtonModule,
		MatSortModule,
	],
	providers: [ApiService,
		{provide: API_URL, useValue: environment.apiUrl}],
	bootstrap: [AppComponent]
})
export class AppModule {}
