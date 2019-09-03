import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GlobalParameterMySuffix from './global-parameter-my-suffix';
import GlobalParameterMySuffixDetail from './global-parameter-my-suffix-detail';
import GlobalParameterMySuffixUpdate from './global-parameter-my-suffix-update';
import GlobalParameterMySuffixDeleteDialog from './global-parameter-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GlobalParameterMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GlobalParameterMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GlobalParameterMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={GlobalParameterMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={GlobalParameterMySuffixDeleteDialog} />
  </>
);

export default Routes;
