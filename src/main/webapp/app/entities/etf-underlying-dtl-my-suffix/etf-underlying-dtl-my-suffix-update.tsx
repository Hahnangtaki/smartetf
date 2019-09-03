import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './etf-underlying-dtl-my-suffix.reducer';
import { IEtfUnderlyingDtlMySuffix } from 'app/shared/model/etf-underlying-dtl-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfUnderlyingDtlMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfUnderlyingDtlMySuffixUpdateState {
  isNew: boolean;
}

export class EtfUnderlyingDtlMySuffixUpdate extends React.Component<
  IEtfUnderlyingDtlMySuffixUpdateProps,
  IEtfUnderlyingDtlMySuffixUpdateState
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
      const { etfUnderlyingDtlEntity } = this.props;
      const entity = {
        ...etfUnderlyingDtlEntity,
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
    this.props.history.push('/entity/etf-underlying-dtl-my-suffix');
  };

  render() {
    const { etfUnderlyingDtlEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfUnderlyingDtl.home.createOrEditLabel">Create or edit a EtfUnderlyingDtl</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfUnderlyingDtlEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-underlying-dtl-my-suffix-id">ID</Label>
                    <AvInput id="etf-underlying-dtl-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="underlyingIdLabel" for="etf-underlying-dtl-my-suffix-underlyingId">
                    Underlying Id
                  </Label>
                  <AvField
                    id="etf-underlying-dtl-my-suffix-underlyingId"
                    type="string"
                    className="form-control"
                    name="underlyingId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="stockCodeLabel" for="etf-underlying-dtl-my-suffix-stockCode">
                    Stock Code
                  </Label>
                  <AvField
                    id="etf-underlying-dtl-my-suffix-stockCode"
                    type="text"
                    name="stockCode"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="weightLabel" for="etf-underlying-dtl-my-suffix-weight">
                    Weight
                  </Label>
                  <AvField id="etf-underlying-dtl-my-suffix-weight" type="string" className="form-control" name="weight" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-underlying-dtl-my-suffix" replace color="info">
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
  etfUnderlyingDtlEntity: storeState.etfUnderlyingDtl.entity,
  loading: storeState.etfUnderlyingDtl.loading,
  updating: storeState.etfUnderlyingDtl.updating,
  updateSuccess: storeState.etfUnderlyingDtl.updateSuccess
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
)(EtfUnderlyingDtlMySuffixUpdate);
