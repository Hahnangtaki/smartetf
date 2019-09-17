import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MiMySuffix from './mi-my-suffix';
import MiMySuffixDetail from './mi-my-suffix-detail';
import MiMySuffixUpdate from './mi-my-suffix-update';
import MiMySuffixDeleteDialog from './mi-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MiMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MiMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MiMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={MiMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MiMySuffixDeleteDialog} />
  </>
);

export default Routes;
