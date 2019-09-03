import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './global-parameter-my-suffix.reducer';
import { IGlobalParameterMySuffix } from 'app/shared/model/global-parameter-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IGlobalParameterMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IGlobalParameterMySuffixUpdateState {
  isNew: boolean;
}

export class GlobalParameterMySuffixUpdate extends React.Component<
  IGlobalParameterMySuffixUpdateProps,
  IGlobalParameterMySuffixUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { globalParameterEntity } = this.props;
      const entity = {
        ...globalParameterEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/global-parameter-my-suffix');
  };

  render() {
    const { globalParameterEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.globalParameter.home.createOrEditLabel">Create or edit a GlobalParameter</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : globalParameterEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="global-parameter-my-suffix-id">ID</Label>
                    <AvInput id="global-parameter-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="prmIdLabel" for="global-parameter-my-suffix-prmId">
                    Prm Id
                  </Label>
                  <AvField
                    id="global-parameter-my-suffix-prmId"
                    type="text"
                    name="prmId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      minLength: { value: 20, errorMessage: 'This field is required to be at least 20 characters.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prmNameLabel" for="global-parameter-my-suffix-prmName">
                    Prm Name
                  </Label>
                  <AvField
                    id="global-parameter-my-suffix-prmName"
                    type="text"
                    name="prmName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prmTypeLabel" for="global-parameter-my-suffix-prmType">
                    Prm Type
                  </Label>
                  <AvField
                    id="global-parameter-my-suffix-prmType"
                    type="text"
                    name="prmType"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="appTypeLabel" for="global-parameter-my-suffix-appType">
                    App Type
                  </Label>
                  <AvField id="global-parameter-my-suffix-appType" type="text" name="appType" />
                </AvGroup>
                <AvGroup>
                  <Label id="intValLabel" for="global-parameter-my-suffix-intVal">
                    Int Val
                  </Label>
                  <AvField id="global-parameter-my-suffix-intVal" type="string" className="form-control" name="intVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="floatValLabel" for="global-parameter-my-suffix-floatVal">
                    Float Val
                  </Label>
                  <AvField id="global-parameter-my-suffix-floatVal" type="string" className="form-control" name="floatVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="strValLabel" for="global-parameter-my-suffix-strVal">
                    Str Val
                  </Label>
                  <AvField id="global-parameter-my-suffix-strVal" type="text" name="strVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateValLabel" for="global-parameter-my-suffix-dateVal">
                    Date Val
                  </Label>
                  <AvField id="global-parameter-my-suffix-dateVal" type="date" className="form-control" name="dateVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="showLabel" check>
                    <AvInput id="global-parameter-my-suffix-show" type="checkbox" className="form-control" name="show" />
                    Show
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="editLabel" check>
                    <AvInput id="global-parameter-my-suffix-edit" type="checkbox" className="form-control" name="edit" />
                    Edit
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/global-parameter-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  globalParameterEntity: storeState.globalParameter.entity,
  loading: storeState.globalParameter.loading,
  updating: storeState.globalParameter.updating,
  updateSuccess: storeState.globalParameter.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GlobalParameterMySuffixUpdate);
