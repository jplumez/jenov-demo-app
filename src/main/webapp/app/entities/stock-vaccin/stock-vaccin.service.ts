import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStockVaccin } from 'app/shared/model/stock-vaccin.model';

type EntityResponseType = HttpResponse<IStockVaccin>;
type EntityArrayResponseType = HttpResponse<IStockVaccin[]>;

@Injectable({ providedIn: 'root' })
export class StockVaccinService {
  public resourceUrl = SERVER_API_URL + 'api/stock-vaccins';

  constructor(protected http: HttpClient) {}

  create(stockVaccin: IStockVaccin): Observable<EntityResponseType> {
    return this.http.post<IStockVaccin>(this.resourceUrl, stockVaccin, { observe: 'response' });
  }

  update(stockVaccin: IStockVaccin): Observable<EntityResponseType> {
    return this.http.put<IStockVaccin>(this.resourceUrl, stockVaccin, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStockVaccin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStockVaccin[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
