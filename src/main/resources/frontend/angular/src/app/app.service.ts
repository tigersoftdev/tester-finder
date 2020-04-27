import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Device } from './interfaces/device';
import { Tester } from './interfaces/tester';
import { API_URL } from './app.tokens';

@Injectable({
	providedIn: 'root'
})
export class ApiService {

	constructor(@Inject(API_URL) private apiUrl: string,
				private readonly http: HttpClient) {
	}

	getCountries(): Observable<string[]> {
		return this.http.get<string[]>(`${this.apiUrl}/countries`);
	}

	getDevices(): Observable<Device[]> {
		return this.http.get<Device[]>(`${this.apiUrl}/devices`);
	}

	getTesters(countries: string[], deviceIds: number[]): Observable<Tester[]> {
		const countriesParam = this.getUrlParam(countries);
		const devicesParam = this.getUrlParam(deviceIds);
		const path = `${this.apiUrl}/testers?countries=${countriesParam}&devices_ids=${devicesParam}`;
		return this.http.get<Tester[]>(path);
	}

	private getUrlParam(array: Array<string | number>): string {
		return array ? array.join(',') : '';
	}

}
