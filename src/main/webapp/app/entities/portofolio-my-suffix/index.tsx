import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PortofolioMySuffix from './portofolio-my-suffix';
import PortofolioMySuffixDetail from './portofolio-my-suffix-detail';
import PortofolioMySuffixUpdate from './portofolio-my-suffix-update';
import PortofolioMySuffixDeleteDialog from './portofolio-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PortofolioMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PortofolioMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PortofolioMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={PortofolioMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PortofolioMySuffixDeleteDialog} />
  </>
);

export default Routes;
