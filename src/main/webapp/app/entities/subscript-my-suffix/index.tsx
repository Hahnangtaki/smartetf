import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SubscriptMySuffix from './subscript-my-suffix';
import SubscriptMySuffixDetail from './subscript-my-suffix-detail';
import SubscriptMySuffixUpdate from './subscript-my-suffix-update';
import SubscriptMySuffixDeleteDialog from './subscript-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SubscriptMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SubscriptMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SubscriptMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={SubscriptMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SubscriptMySuffixDeleteDialog} />
  </>
);

export default Routes;
