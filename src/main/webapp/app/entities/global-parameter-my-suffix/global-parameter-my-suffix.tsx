import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './global-parameter-my-suffix.reducer';
import { IGlobalParameterMySuffix } from 'app/shared/model/global-parameter-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGlobalParameterMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class GlobalParameterMySuffix extends React.Component<IGlobalParameterMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { globalParameterList, match } = this.props;
    return (
      <div>
        <h2 id="global-parameter-my-suffix-heading">
          Global Parameters
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Global Parameter
          </Link>
        </h2>
        <div className="table-responsive">
          {globalParameterList && globalParameterList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Prm Id</th>
                  <th>Prm Name</th>
                  <th>Prm Type</th>
                  <th>App Type</th>
                  <th>Int Val</th>
                  <th>Float Val</th>
                  <th>Str Val</th>
                  <th>Date Val</th>
                  <th>Show</th>
                  <th>Edit</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {globalParameterList.map((globalParameter, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${globalParameter.id}`} color="link" size="sm">
                        {globalParameter.id}
                      </Button>
                    </td>
                    <td>{globalParameter.prmId}</td>
                    <td>{globalParameter.prmName}</td>
                    <td>{globalParameter.prmType}</td>
                    <td>{globalParameter.appType}</td>
                    <td>{globalParameter.intVal}</td>
                    <td>{globalParameter.floatVal}</td>
                    <td>{globalParameter.strVal}</td>
                    <td>
                      <TextFormat type="date" value={globalParameter.dateVal} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{globalParameter.show ? 'true' : 'false'}</td>
                    <td>{globalParameter.edit ? 'true' : 'false'}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${globalParameter.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${globalParameter.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${globalParameter.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Global Parameters found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ globalParameter }: IRootState) => ({
  globalParameterList: globalParameter.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GlobalParameterMySuffix);
