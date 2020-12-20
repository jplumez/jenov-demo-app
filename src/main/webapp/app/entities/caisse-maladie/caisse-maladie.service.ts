import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICaisseMaladie } from 'app/shared/model/caisse-maladie.model';

type EntityResponseType = HttpResponse<ICaisseMaladie>;
type EntityArrayResponseType = HttpResponse<ICaisseMaladie[]>;

@Injectable({ providedIn: 'root' })
export class CaisseMaladieService {
  public resourceUrl = SERVER_API_URL + 'api/caisse-maladies';

  constructor(protected http: HttpClient) {}

  create(caisseMaladie: ICaisseMaladie): Observable<EntityResponseType> {
    return this.http.post<ICaisseMaladie>(this.resourceUrl, caisseMaladie, { observe: 'response' });
  }

  update(caisseMaladie: ICaisseMaladie): Observable<EntityResponseType> {
    return this.http.put<ICaisseMaladie>(this.resourceUrl, caisseMaladie, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICaisseMaladie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICaisseMaladie[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
